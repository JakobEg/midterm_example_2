import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/**
 * ExpensesTracker
 */
public class ExpensesTracker {

    private int income;
    private List<Invoice> yearlyInvoices;

    public ExpensesTracker(long income){
        this.income = Math.round(income);
        yearlyInvoices = new ArrayList<Invoice>();

    }

    public void addInvoice(Invoice i){
        yearlyInvoices.add(i);
    }

    public int monthExpenses(int m){
        int total = 0;
        for (Invoice invoice : yearlyInvoices) {
            if (invoice.getMonth()==m) {
                total+=invoice.getAmount();
            }
        }
        return total;
    }
    public int monthSaving(int m){
        return income - monthExpenses(m);
    }

    public void readInvoice(){
        Scanner stdin = new Scanner(System.in);
        int day = stdin.nextInt();
        int month = stdin.nextInt();
        int year = stdin.nextInt();
        String payee = stdin.next();
        Invoice newInvoice = new Invoice(year, month, day, payee);
        yearlyInvoices.add(newInvoice);
        stdin.close();
    }

    public void clearMonthInvoices(int m){
        for (Invoice invoice : yearlyInvoices) {
            if (invoice.getMonth()==m) {
                yearlyInvoices.remove(invoice);
            }
        } 
    }

    public void listYearlySavings(int m){
        for (int i = 0; i < 12; i++) {
            if (monthSaving(i)==0) {
                System.out.println("no saving in month" + i);
            }else{
                System.out.println("The saved amount in month " + i + "is " + monthSaving(i));
            }
        }
    }

    public HashMap<String, Integer> payeeAmount(){
        HashMap<String, Integer> map = new HashMap();

        for (Invoice invoice : yearlyInvoices) {
            if (map.containsKey(invoice.getPayee())) {
                int mapAmount = map.get(invoice.getPayee());
                map.replace(invoice.getPayee(), mapAmount+invoice.getAmount());

            }else{
                map.put(invoice.getPayee(), invoice.getAmount());
            }
        }
        return map;
    }
}