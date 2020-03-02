package test;

public class mm {

	public static void main(String[] args) {
		String sql="select * from order1  where 1=1 ";
		String a=" and odate>='2019-10-13'",b=" and custname='北京市东城区崇文幼儿园食堂'",c="",d="";
		if(!"".equals(a)) {
			sql=sql+a;
		}
		if(!"".equals(b)) {
			
			sql=sql+b;
		}
		if(!"".equals(c)) {
			
			sql=sql+c;
		}
		if(!"".equals(d)) {
			
			sql=sql+d;
		}
		System.out.println("sql="+sql);
	}
}
