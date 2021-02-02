public class Users extends Wiki {
	private String username; // for now

	public Users(String username){
			this.username = username; 
	}

	public void print(){
		System.out.print(username + ' ');
	}

}
