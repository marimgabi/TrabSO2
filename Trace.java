import java.math.BigInteger;

public class Trace {

    private String memoryAccess; //deslocamento
    private String operation;
    private String page;

    public Trace(String memoryAccess, String operation, int tamPagina) {


        //parse de string de hexadecimal para binário
        String string = new BigInteger(memoryAccess, 16).toString(2);

        //Preenchendo zeros ignorados
        while (string.length()<32)
            string="0"+string;

        this.memoryAccess = new String(string.substring((32-(int)(Math.log(tamPagina)/Math.log(2))),32));
        this.operation = operation;
        this.page =  new String(string).substring(0, (32-(int)(Math.log(tamPagina)/Math.log(2))));
    }

    public String getMemoryAccess() {
        return memoryAccess;
    }

    public void setMemoryAccess(String memoryAccess) {
        this.memoryAccess = memoryAccess;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Trace{" +
                "memoryAccess='" + memoryAccess + '\'' +
                ", operation='" + operation + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
