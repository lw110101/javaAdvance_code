package idCard;

public class Test {

	public static void main(String[] args) {
		System.out.println(transIDCard15To18("362324971217181"));
		
	}
	private static String transIDCard15To18(String IdCardNO) {
		String cardNo=null;
		if(null!=IdCardNO && IdCardNO.trim().length()==15) {
			IdCardNO=IdCardNO.trim();
			StringBuffer sb=new StringBuffer(IdCardNO);
			sb.insert(6, "19");
			sb.append(transCardLastNo(sb.toString()));
			cardNo=sb.toString();
		}
		return cardNo;
	}
	/**
	 * 15Î»²¹È«
	 */
	private static String transCardLastNo(String newCardId) {
		char[] ch=newCardId.toCharArray();
		int m=0;
		int[] code= {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
		char[] verCode=new char[] {'1','0','X','9','8','7','6','5','4','3','2'};
		for (int i = 0; i < newCardId.length(); i++) {
			m+=(ch[i]-'0')*code[i];
		}
		int residue=m%11;
		return String.valueOf(verCode[residue]);
	}
}
