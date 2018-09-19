package action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionMapping;
import bean.ActionMappingManager;
import bean.Result;

/**
 * 核心控制器 拦截所有后缀为.action的请求
 * 
 * @author hasee 上午12:19:16
 */
@WebServlet(urlPatterns = { "*.action" })
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionMappingManager manager;

	@Override
	public void init() throws ServletException {
		manager = new ActionMappingManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1。获取请求uri,得到请求路径名称
			String uri = request.getRequestURI();
			// 得到 login
			String actionName = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".action"));

			// 2. 根据路径名称，读取配置文件，得到类的全名 【cn..action.LoginAction】
			ActionMapping actionMapping = manager.getActionMapping(actionName);
			// 类名
			String className = actionMapping.getClassName();
			// 当前请求的处理方法
			String method = actionMapping.getMethod();
			// 3. 反射： 创建对象，调用方法； 获取方法返回的标记
			Class<?> clazz = Class.forName(className);
			Object object = clazz.newInstance();
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			Object returnFlag = m.invoke(object, request, response);
			// 4. 拿到标记，读取配置文件得到标记对应的页面 、 跳转类型
			Map<String, Result> resultMap = actionMapping.getResult();
			Result result = resultMap.get(returnFlag);
			String type = result.getType();
			String page = result.getUri();
			//
			if ("redirect".equals(type)) {
				response.sendRedirect(request.getContextPath() + page);
			} else {
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
