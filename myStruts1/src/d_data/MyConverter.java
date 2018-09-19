package d_data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class MyConverter extends StrutsTypeConverter {
	// ����֧��yyyyMMdd hh:mm:ss yyyy/MM/dd hh:mm:ss yyyy MM dd hh:mm:ss yyyy-MM-dd
	// hh��mm:ss
	SimpleDateFormat[] sdf = { new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss"), new SimpleDateFormat("yyyyMMdd hh:mm:ss"),
			new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"), new SimpleDateFormat("yyyy MM dd hh:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd hh��mm:ss") };

	/**
	 * @param Map
	 *            context, ��ǰ�����Ķ��� String[] values, ��ȡ��jsp���� Class toClass ��Ҫת��������
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {

		if (values.length == 0 || values == null) {
			return null;
		}
		if (Date.class != toClass) {
			return null;
		}
		for (int i = 0; i < sdf.length; i++) {
			try {
				return sdf[i].parse(values[0]);

			} catch (ParseException e) {
				continue;    //������ѭ����ִ����һ��ѭ��
			}
		}
		return null;

	}

	@Override
	public String convertToString(Map context, Object o) {
		return null;
	}

}
