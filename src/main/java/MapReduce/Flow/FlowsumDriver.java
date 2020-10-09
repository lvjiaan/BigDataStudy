package MapReduce.Flow;

/**
 * @Describe:
 * @Author��lvja
 * @Date��2020/10/9 14:53
 * @Modifier��
 * @ModefiedDate:
 */
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FlowsumDriver {

    public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {

// �������·����Ҫ�����Լ�������ʵ�ʵ��������·������
        args = new String[] { "e:/input/flow.txt", "e:/output" };

        // 1 ��ȡ������Ϣ������job����ʵ��
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 6 ָ���������jar�����ڵı���·��
        job.setJarByClass(FlowsumDriver.class);

        // 2 ָ����ҵ��jobҪʹ�õ�mapper/Reducerҵ����
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        // 3 ָ��mapper������ݵ�kv����
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 4 ָ��������������ݵ�kv����
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 5 ָ��job������ԭʼ�ļ�����Ŀ¼
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 ��job�����õ���ز������Լ�job���õ�java�����ڵ�jar���� �ύ��yarnȥ����
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
