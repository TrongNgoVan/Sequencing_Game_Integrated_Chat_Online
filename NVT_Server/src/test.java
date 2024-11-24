
// test dãy số gửi từ client về server

public class test{
    public static void main(String[] args) {
        String res = "SUBMIT_RESULT;" + "loginUser" + ";" + "competitor" + ";" + "roomIdPresent" + ";" + "1,2,3,4,5,6" + ";" + "time"; 
        String[] tmp = res.split(";");
        for (int i=0;i<tmp.length;i++){
            System.out.println(i + ": " + tmp[i]  );
        }
    }
}
