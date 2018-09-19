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
 * ���������ļ�����װ����myStruts.xml
 * 
 * @author hasee ����11:37:50
 */
public class ActionMappingManager {

	// ����action�ļ��� key:����·�� value:action�ڵ�
	private Map<String, ActionMapping> allAction;

	// �����ʼ��
	public ActionMappingManager() {
		allAction = new HashMap<String, ActionMapping>();
		this.init();
	}

	// ��������·������action��ӳ��
	public ActionMapping getActionMapping(String actionName) {
		if (actionName == null) {
			throw new RuntimeException("����Ĳ���������鿴myStruts.xml��·��");
		}

		ActionMapping actionMapping = allAction.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("·����myStruts.xml���Ҳ�����");
		}

		return actionMapping;
	}

	/**
	 * ��ʼ�� ���������ļ�
	 */

	private void init() {
		/************* Dom4j��ȡ���� ***************/
		try {
			// ��ȡ������
			SAXReader reader = new SAXReader();
			// �õ�xml���ļ���
			InputStream in = this.getClass().getResourceAsStream("/myStruts.xml");
			// �õ��ĵ���
			Document doc = reader.read(in);
			// �õ����ڵ�
			Element rootElement = doc.getRootElement();
			// ��ȡpackage�ڵ�
			Element packElem = rootElement.element("package");
			// �õ�package�µ�����action�ӽڵ�
			@SuppressWarnings("unchecked")
			List<Element> actionElems = packElem.elements("action");
			// ����
			for (Element actionElem : actionElems) {
				// ��װһ��actionMapping����
				ActionMapping actionMapping = new ActionMapping();
				// ��װ��������
				actionMapping.setName(actionElem.attributeValue("name"));
				actionMapping.setClassName(actionElem.attributeValue("class"));
				actionMapping.setMethod(actionElem.attributeValue("method"));
				// ��װation�µĽ����ͼ
				Map<String, Result> results = new HashMap<String, Result>();
				// ��ȡ���е�result�ӽڵ�
				@SuppressWarnings("unchecked")
				Iterator<Element> resultElems = actionElem.elementIterator("result");
				// ����
				while (resultElems.hasNext()) {
					// ��װ����
					Result result = new Result();
					Element resultElem = resultElems.next();
					
					result.setName(resultElem.attributeValue("name"));
					result.setType(resultElem.attributeValue("type"));
					result.setUri(resultElem.getTextTrim());
					// ��result������뵽������
					results.put(result.getName(), result);

					actionMapping.setResult(results);

					// ��actionMaping��ӵ�������
					allAction.put(actionMapping.getName(), actionMapping);
				}
			}
			//
		} catch (Exception e) {
			throw new RuntimeException("����ʱ��ʼ������", e);
		}

	}

}
