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
 * ���Ŀ����� �������к�׺Ϊ.action������
 * 
 * @author hasee ����12:19:16
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
			// 1����ȡ����uri,�õ�����·������
			String uri = request.getRequestURI();
			// �õ� login
			String actionName = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".action"));

			// 2. ����·�����ƣ���ȡ�����ļ����õ����ȫ�� ��cn..action.LoginAction��
			ActionMapping actionMapping = manager.getActionMapping(actionName);
			// ����
			String className = actionMapping.getClassName();
			// ��ǰ����Ĵ�����
			String method = actionMapping.getMethod();
			// 3. ���䣺 �������󣬵��÷����� ��ȡ�������صı��
			Class<?> clazz = Class.forName(className);
			Object object = clazz.newInstance();
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			Object returnFlag = m.invoke(object, request, response);
			// 4. �õ���ǣ���ȡ�����ļ��õ���Ƕ�Ӧ��ҳ�� �� ��ת����
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
