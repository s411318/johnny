package aTools;

import java.net.*;
import java.io.*;

class GetURLConnectionContent_Ver1 {
	public static void main(String args[]) {
		
		URL url = null;

		try {
			url = new URL("https://tw.yahoo.com"); // �إ�URL����url , �H ISBN ��Ѭ���
		} catch (MalformedURLException e) {
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
				if (data.contains("<title>")) {
					System.out.println(data.substring(data.indexOf("<title>") + 7, data.indexOf("</title>")));
				}
			}

			br.close();
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}