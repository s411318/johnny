package aTools;

import java.net.*;
import java.io.*;
import java.io.*;

class GetURLConnectionContent_Ver3 {
	public static void main(String args[]) {
		
		URL url = null;

		try {
			url = new URL("http://maps.googleapis.com/maps/api/geocode/xml?address="+java.net.URLEncoder.encode("�x�_�����s�Ϥ��s�_���@�q120��","UTF-8")+"&sensor=false&language=zh-TW"); // �إ�URL����url , �H ����x�_��(���a�}����g�n�׬���)
		} catch (MalformedURLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			// �HURL����إ�URLConnection����
			URLConnection urlConn = url.openConnection();
			// �HURLConnection������o��J��Ƭy
			InputStream ins = urlConn.getInputStream();
			
			// �إ߸g�n��.xml
			//C:\Android_workStation\PetYM_SQL_Web
			BufferedReader in = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			BufferedWriter out = new BufferedWriter(new FileWriter("�g�n��.xml"));
			char[] buf = new char[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
			ins.close();
			System.out.println("�w�إ߸g�n��.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}