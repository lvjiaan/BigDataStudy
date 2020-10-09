package MapReduce.Flow;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @Describe:
 * @Author��lvja
 * @Date��2020/10/9 14:51
 * @Modifier��
 * @ModefiedDate:
 */


public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context)throws IOException, InterruptedException {

        long sum_upFlow = 0;
        long sum_downFlow = 0;

        // 1 ��������bean�������е��������������������ֱ��ۼ�
        for (FlowBean flowBean : values) {
            sum_upFlow += flowBean.getUpFlow();
            sum_downFlow += flowBean.getDownFlow();
        }

        // 2 ��װ����
        FlowBean resultBean = new FlowBean(sum_upFlow, sum_downFlow);

        // 3 д��
        context.write(key, resultBean);
    }
}
