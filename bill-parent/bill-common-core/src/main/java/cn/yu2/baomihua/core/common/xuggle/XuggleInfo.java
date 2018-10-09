package cn.yu2.baomihua.core.common.xuggle;

import java.io.File;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IContainer;

import cn.yu2.baomihua.core.common.upload.FileUtil;

/**
 * 
 * 视频媒体信息
 * 
 * <p>
 * 解码并获取截取某一帧保存为图片
 * </p>
 * 
 * @author hubin
 */
public class XuggleInfo extends MediaListenerAdapter {

	private static final long MICRO_SECONDS_BETWEEN_FRAMES = (long) (Global.DEFAULT_PTS_PER_SECOND * 1.0D);
	private static long mLastPtsWrite = Global.NO_PTS;
	private int mVideoStreamIndex = -1;
	private boolean takeImage = true;
	private IContainer container;
	private String imagePath;
	private String imageSuffix = "png";
	private double imageSecond = 1.0D;
	private String imageUrl;


	/**
	 * 视频媒体信息
	 * 
	 * @param fileUrl
	 * 				视频文件地址
	 * @param imagePath
	 * 				保存图片路径
	 * @return
	 */
	public XuggleInfo( String fileUrl, String imagePath ) {
		this.container = getIContainer(fileUrl);
		this.imagePath = imagePath;
	}


	/**
	 * 只获取视频时长
	 * 
	 * @param fileUrl
	 * 				视频文件地址
	 * @param imagePath
	 * 				保存图片路径
	 * @return
	 */
	public XuggleInfo( String fileUrl ) {
		this.container = getIContainer(fileUrl);
	}


	protected XuggleInfo() {
		/**
		 * 保护
		 */
	}


	/**
	 * 执行
	 */
	public void run() {
		IMediaReader reader = ToolFactory.makeReader(getContainer());
		reader.setBufferedImageTypeToGenerate(5);
		reader.addListener(this);
		while ( reader.readPacket() == null ) {
			if ( !takeImage ) {
				reader.removeListener(this);
				break;
			}
		}
	}


	/**
	 * 获取 IContainer
	 */
	public static IContainer getIContainer( String fileUrl ) {
		IContainer container = IContainer.make();
		int result = container.open(fileUrl, IContainer.Type.READ, null);
		if ( result < 0 ) {
			throw new RuntimeException("Failed to open media file");
		}
		return container;
	}


	/**
	 * 处理视频图片
	 */
	public void onVideoPicture( IVideoPictureEvent event ) {
		try {
			if ( event.getStreamIndex().intValue() != this.mVideoStreamIndex ) {
				if ( -1 == this.mVideoStreamIndex ) {
					this.mVideoStreamIndex = event.getStreamIndex().intValue();
				} else {
					return;
				}
			}
			if ( mLastPtsWrite == Global.NO_PTS ) {
				mLastPtsWrite = event.getTimeStamp().longValue() - MICRO_SECONDS_BETWEEN_FRAMES;
			}
			if ( event.getTimeStamp().longValue() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES && takeImage ) {
				double seconds = event.getTimeStamp().longValue() / Global.DEFAULT_PTS_PER_SECOND;
				if ( seconds >= getImageSecond() ) {
					String pathname = FileUtil.getPathName(getImagePath(), getImageSuffix());
					boolean writeImage = ImageIO.write(event.getImage(), getImageSuffix(), new File(pathname));
					if ( writeImage ) {
						this.imageUrl = pathname;
						takeImage = false;
					}
				}
				mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
			}
		} catch ( Exception e ) {
			takeImage = false;
			e.printStackTrace();
		}
	}


	/**
	 * 删除图片文件
	 */
	public boolean delImage() {
		File file = new File(this.getImageUrl());
		if ( file != null && file.exists() ) {
			return file.delete();
		}
		return false;
	}


	public IContainer getContainer() {
		return container;
	}


	public String getImagePath() {
		return imagePath;
	}


	public String getImageSuffix() {
		return imageSuffix;
	}


	public void setImageSuffix( String imageSuffix ) {
		this.imageSuffix = imageSuffix;
	}


	public double getImageSecond() {
		return imageSecond;
	}


	public void setImageSecond( double imageSecond ) {
		this.imageSecond = imageSecond;
	}


	public String getImageUrl() {
		return imageUrl;
	}

}
