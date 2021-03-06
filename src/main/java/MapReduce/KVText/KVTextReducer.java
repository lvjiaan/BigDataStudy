package MapReduce.KVText;

/**
 * @Describe:
 * @Author��lvja
 * @Date��2020/10/10 14:19
 * @Modifier��
 * @ModefiedDate:
 */
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KVTextReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

    LongWritable v = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values,	Context context) throws IOException, InterruptedException {

        long sum = 0L;

        // 1 ����ͳ��
        for (LongWritable value : values) {
            sum += value.get();
        }

        v.set(sum);

        // 2 ���
        context.write(key, v);
    }
}
