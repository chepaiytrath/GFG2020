public class OddEvenMultithreading {
    public static void main(String[] args) {
        Object obj = new Object();
        Thread t1 = new Thread(new EvenTask(obj));
        Thread t2 = new Thread(new OddTask(obj));

        t1.start();
        t2.start();

    }

    static class EvenTask implements Runnable{
        Object obj;

        EvenTask(Object obj){
            this.obj = obj;
        }

        @Override
        public void run() {
            for(int i = 0; i <= 10; i=i+2){
                synchronized (obj){
                    if(i % 2 == 0){
                        System.out.println(i);
                        obj.notify();
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class OddTask implements Runnable{
        Object obj;

        OddTask(Object obj){
            this.obj = obj;
        }

        @Override
        public void run() {
            for(int i = 1; i <= 10; i=i+2){
                synchronized (obj){
                    if(i % 2 != 0){
                        System.out.println(i);
                        obj.notify();
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
