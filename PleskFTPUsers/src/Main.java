


public class Main {

	public static void main(String[] args) throws Exception {
		String login = "/*Enter Plesk username*/";
		String password = "/*Enter Plesk Password*/";
		String host = ("/*Enter Plesk domain (without http!!! it should look like plesk2.domain.co.il)*/");
		PleskApiClient client = new PleskApiClient(host);
		client.setCredentials(login, password);
		String[] Clients = FindFolders.Folders("V:/Site/20140916/ftpUsers/");
		System.out.println("there r " + Clients.length + " clients to create");
		for(int a=0 ; a<=Clients.length-1 ; a++){
			
			if(Clients[a]!=("FROMLP") && Integer.valueOf(Clients[a]) < 90000){
				String[] Lic = FindFolders.Folders("V:/Site/20140916/ftpUsers/" + Clients[a]);
				System.out.println("client " + Clients[a] + " has " + Lic.length + " licenses");
				for(int b=0 ; b<=Lic.length-1 ; b++){
					String Username = "L" + Clients[a] + "AT" + Lic[b] + "A";
					String Password = "t" + Lic[b] +"!";
					String Location = "\\ftpusers\\" + Clients[a] + "A\\" + Lic[b] + "A\\";
					System.out.println("Username is : " + Username);
					System.out.println("Password is : " + Password);
					System.out.println("Location is : " + Location);
					String request = "<packet>" +
										"<ftp-user>" + 
												"<add>" +
													"<name>" + Username +"</name>" + 
													"<password>" + Password + "</password>"+
													"<home>" + Location + "\\FROMHZ\\TUPAL" + "</home>"+
													"<create-non-existent>true</create-non-existent>"+
													"<permissions>"+
														"<read>true</read>"+
														"<write>true</write>"+
													"</permissions>"+
													"<webspace-name>terem.co.il</webspace-name>"+
												"</add>"+
										"</ftp-user>" + 
									"</packet>";
					System.out.println(request);
					String response = client.request(request);
					request = "<packet>" +
							"<ftp-user>" + 
									"<set>" +
										"<filter>" + 
											"<name>" + Username + "</name>"+
										"</filter>" +
										"<values>" +
											"<home>" +  Location + "FROMLP\\TUPAL\\" + "</home>" + 
											"<create-non-existent>true</create-non-existent>"+
										"</values>"+
										"<webspace-name>terem.co.il</webspace-name>"+
									"</set>"+
							"</ftp-user>" + 
						"</packet>";
					response = client.request(request);
					request = "<packet>" +
							"<ftp-user>" + 
									"<set>" +
										"<filter>" + 
											"<name>" + Username + "</name>"+
										"</filter>" +
										"<values>" +
											"<home>" +  Location + "</home>" + 
											"<create-non-existent>true</create-non-existent>"+
										"</values>"+
										"<webspace-name>terem.co.il</webspace-name>"+
									"</set>"+
							"</ftp-user>" + 
						"</packet>";
					response = client.request(request);
					System.out.println(response);
				}
			}
		}
	}
}