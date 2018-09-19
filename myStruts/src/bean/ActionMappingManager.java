package bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 加载配置文件，封装整个myStruts.xml
 * 
 * @author hasee 上午11:37:50
 */
public class ActionMappingManager {

	// 保存action的集合 key:请求路径 value:action节点
	private Map<String, ActionMapping> allAction;

	// 构造初始化
	public ActionMappingManager() {
		allAction = new HashMap<String, ActionMapping>();
		this.init();
	}

	// 根据请求路径返回action的映射
	public ActionMapping getActionMapping(String actionName) {
		if (actionName == null) {
			throw new RuntimeException("传入的参数有误！请查看myStruts.xml的路径");
		}

		ActionMapping actionMapping = allAction.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("路径在myStruts.xml中找不到！");
		}

		return actionMapping;
	}

	/**
	 * 初始化 加载配置文件
	 */

	private void init() {
		/************* Dom4j读取配置 ***************/
		try {
			// 获取解析器
			SAXReader reader = new SAXReader();
			// 得到xml的文件流
			InputStream in = this.getClass().getResourceAsStream("/myStruts.xml");
			// 得到文档树
			Document doc = reader.read(in);
			// 得到根节点
			Element rootElement = doc.getRootElement();
			// 获取package节点
			Element packElem = rootElement.element("package");
			// 得到package下的所有action子节点
			@SuppressWarnings("unchecked")
			List<Element> actionElems = packElem.elements("action");
			// 遍历
			for (Element actionElem : actionElems) {
				// 封装一个actionMapping对象
				ActionMapping actionMapping = new ActionMapping();
				// 封装对象属性
				actionMapping.setName(actionElem.attributeValue("name"));
				actionMapping.setClassName(actionElem.attributeValue("class"));
				actionMapping.setMethod(actionElem.attributeValue("method"));
				// 封装ation下的结果视图
				Map<String, Result> results = new HashMap<String, Result>();
				// 获取所有的result子节点
				@SuppressWarnings("unchecked")
				Iterator<Element> resultElems = actionElem.elementIterator("result");
				// 遍历
				while (resultElems.hasNext()) {
					// 封装对象
					Result result = new Result();
					Element resultElem = resultElems.next();
					
					result.setName(resultElem.attributeValue("name"));
					result.setType(resultElem.attributeValue("type"));
					result.setUri(resultElem.getTextTrim());
					// 将result对象放入到集合中
					results.put(result.getName(), result);

					actionMapping.setResult(results);

					// 将actionMaping添加到集合中
					allAction.put(actionMapping.getName(), actionMapping);
				}
			}
			//
		} catch (Exception e) {
			throw new RuntimeException("启动时初始化错误", e);
		}

	}

}
