public class Fib{
	public int fib(int a){
		if (a == 1| a==2){return 1;}
		return fib(a-1) + fib(a-2);}
	public static void main(String[] args){
		int a = 10;
		System.out.println(Fib.fib(5));
	}
}
		
