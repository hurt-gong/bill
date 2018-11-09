package cn.yu2.baomihua.bastpay.bptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class reqpost {
    /**
     * 请求连接固定参数
     * @param s 传参地址
     * @return 返回正常连接
     * @throws IOException
     */
    public static URLConnection getConnection(String s) throws IOException {
        URL url = new URL(s);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        return conn;
    }
    /**
     * post请求
     * @param url 地址加接口
     * @param param 请求参数
     * @return 返回响应结果
     * @throws IOException 抛异常
     */
    public static String reqPost(String url, String param) throws IOException {
        System.out.println("请求地址:"+url);
        System.out.println("请求参数:"+param);
        String res = "";
        URLConnection conn = getConnection(url); // POST要求URL中不包含请求参数
        conn.setDoOutput(true); // 必须设置这两个请求属性为true，就表示默认使用POST发送
        conn.setDoInput(true);
        // 请求参数必须使用conn获取的OutputStream输出到请求体参数
        PrintWriter out = new PrintWriter(conn.getOutputStream()); // 用PrintWriter进行包装
        out.println(param);
        out.flush(); // 立即充刷至请求体）PrintWriter默认先写在内存缓存中

        try// 发送正常的请求（获取资源）
        {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("Get Error Occured!");
            e.printStackTrace();
        }
        return res;
    }
}
