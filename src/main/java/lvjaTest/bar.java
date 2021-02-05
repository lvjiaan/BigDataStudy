package lvjaTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @Describe:
 * @Author��lvja
 * @Date��2020/12/21 17:13
 * @Modifier��
 * @ModefiedDate:
 */
public class bar {

    public static void main(String[] args) {

        ExecutorService executor = newFixedThreadPool(10);
        long time3 = System.currentTimeMillis();
        int size=100;
        AtomicInteger count=new AtomicInteger();
        while(size != count.get()){
            executor.execute(()->{
                String progressbar = Progressbar(count.get(), size);
                System.out.print("\r���������:" + ((count.get()+ 1) * 100) / size + "%[" + progressbar + "]" + (count.get()+ 1) + "/" + size + "\t��ʱ:" + getConsuming(time3));
//            System.out.print("\r"+count);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.incrementAndGet();
            });

        }



    }

    public static String getConsuming(long time3){
        long  second = (System.currentTimeMillis() - time3) / 1000;
        long days = second / 86400;//ת������
        second = second % 86400;//ʣ������
        long hours = second / 3600;//ת��Сʱ��
        second = second % 3600;//ʣ������
        long minutes = second / 60;//ת������
        second = second % 60;//ʣ������
        return days+"d"+hours+"h"+minutes+"m"+second+"s";
    }

    public static String Progressbar(int i, int size) {
        String tu = "--------------------";
        if (i > 0 && size > 0) {
            int c = 1;
            if (size > 10) {
                c = size / 10;
            }
            int count = i / c + 1;
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    tu = "--------------------";
                } else {
                    if (j == 1) {
                        tu = "";
                    }
                    tu += ">>";
                    if (j == count - 1) {
                        for (int k = 0; k <= 10 - count; k++) {
                            tu += "--";
                        }
                    }
                }
                if (size - 1 == i) {
                    tu = ">>>>>>>>>>>>>>>>>>>>";
                }
            }
        }
        return tu;
    }




}
