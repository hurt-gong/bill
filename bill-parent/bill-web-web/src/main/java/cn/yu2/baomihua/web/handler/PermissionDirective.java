package cn.yu2.baomihua.web.handler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.view.ViewToolContext;

import com.baomidou.framework.spring.SpringHelper;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;

import cn.yu2.baomihua.manage.module.IPermissionModule;


/**
 * 权限控制自定义标签
 * <p>
 * 
 * @author   wanglulu
 * @date	 2015-11-29 
 * @version  1.0.0	 
 */
public class PermissionDirective extends Directive {

	@Override
	public String getName() {

		return "permission";

	}


	@Override
	public int getType() {

		return BLOCK;

	}


	@Override
	public boolean render( InternalContextAdapter context, Writer writer, Node node ) throws IOException,
		ResourceNotFoundException, ParseErrorException, MethodInvocationException {

		//获取权限值code
		SimpleNode codeNode = (SimpleNode) node.jjtGetChild(0);
		String code = (String) codeNode.value(context);
		//获取用户权限code集合
		ViewToolContext viewToolContext = (ViewToolContext) context.getInternalUserContext();
		SSOToken st = (SSOToken) SSOHelper.attrToken(viewToolContext.getRequest());
		IPermissionModule permissionModuel = (IPermissionModule) SpringHelper
				.getBean(IPermissionModule.class);
		Set<String> permissionCodes = permissionModuel.getPermission(st.getId()).getBody();
		//判断用户权限code集合中是否包含 code
		if ( permissionCodes.contains(code) ) {
			Node body = node.jjtGetChild(1);
			StringWriter sw = new StringWriter();
			body.render(context, writer);
			writer.write(sw.toString());
			return true;
		}
		return true;

	}

}
