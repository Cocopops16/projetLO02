package Controleur;

public class MsgBox {
	private String msg;
	
	public MsgBox() {
		this.msg = null;
	}
	
	public synchronized String readMsg() {
		while(this.msg == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String msgLu = this.msg;
		this.msg = null;
		this.notifyAll();
		return msgLu;
	}
	
	public synchronized void addMsg(String newMsg) {
		while(this.msg != null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.msg = newMsg;
		this.notifyAll();
	}
}
