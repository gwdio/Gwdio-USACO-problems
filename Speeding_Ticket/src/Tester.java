public class Tester {
    Data data;
    private int max;
    private int indexN;
    private int indexM;
    private int cReq;
    private int cReal;
    public Tester(){max=0;data=null;}
    public Tester(Data data){
        this.data = data;
        max = indexM = indexN = 0;
        loadCReal(data.getRealD(indexM));
        loadCReq(data.getReqD(indexN));
    }
    public int run(){
        while((indexM<data.getM()||(indexN<data.getN()))){
            proceed();
        }
        return max;
    }
    private void proceed(){
        if(cReq>cReal){ //if required distance is longer than other distance
            max = Math.max(max, (data.getRealS(indexM)-data.getReqS(indexN))); //sets max to the highest infraction
            cReq -= cReal;
            indexM++;
            loadCReal(data.getRealD(indexM));
        }
        if(cReal>cReq){
            max = Math.max(max, (data.getRealS(indexM)-data.getReqS(indexN))); //sets max to the highest infraction
            cReal -= cReq;
            indexN++;
            loadCReq(data.getReqD(indexN));
        }
        if(cReal==cReq){
            max = Math.max(max, (data.getRealS(indexM)-data.getReqS(indexN))); //sets max to the highest infraction
            indexN++;
            indexM++;
            if(!((indexN==data.getN())&&(indexM==data.getM()))){
                loadCReal(data.getRealD(indexM));
                loadCReq(data.getReqD(indexN));
            }
        }
    }
    private int loadCReq(){
        return cReq;
    }
    private void loadCReq(int load){
        cReq = load;
    }
    private int loadCReal(){
        return cReal;
    }
    private void loadCReal(int load){
        cReal = load;
    }
}