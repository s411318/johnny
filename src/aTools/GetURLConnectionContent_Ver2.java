package aTools;

import java.net.*;
import java.io.*;
import java.io.*;

class GetURLConnectionContent_Ver2 {
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

			// �إ�URL��Ƭy
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String data;
			while ((data = br.readLine()) != null) {
				//System.out.println(data);
				if (data.contains("<lat>")) {
					System.out.println(data.substring(data.indexOf("<lat>") + 5, data.indexOf("</lat>")));
				}
			}

			br.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}