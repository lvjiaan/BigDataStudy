package MapReduce.Flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Describe:
 * @Author��lvja
 * @Date��2020/10/9 14:43
 * @Modifier��
 * @ModefiedDate:
 */
public class FlowBean implements Writable {
    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;


    public FlowBean() {
        super();
    }

    public FlowBean(long sum_upFlow, long sum_downFlow) {
        this.upFlow=sum_upFlow;
        this.downFlow=sum_downFlow;
        this.sumFlow=upFlow+downFlow;
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }


    /**
     * �����Ҫ���Զ����bean����key�д��䣬����Ҫʵ��Comparable�ӿڣ���ΪMapReduce���е�Shuffle����Ҫ���key����������
     */
    public int compareTo(FlowBean o) {
        // �������У��Ӵ�С
        return this.sumFlow > o.getSumFlow() ? -1 : 1;
    }


    // 6 ��дtoString���������������ӡ���ı�
    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public void set(long downFlow, long upFlow) {
        this.downFlow=downFlow;
        this.upFlow=upFlow;
    }
}
