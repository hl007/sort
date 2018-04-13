public class HeapMin {  

    private int[] Heap;  //存储堆的数组
    private int maxsize;  //数组容量
    private int size;  //堆大小
  
  
	//初始化堆，从数组index=1开始存储
    public HeapMin(int max) {  
        maxsize = max;  
        Heap = new int[maxsize];   
        size = 0;  
        Heap[0] = Integer.MIN_VALUE;  
    }  
  
  
	//返回父节点的左孩子节点索引
    private int leftchild(int fpos) {  
        return 2 * fpos;  
    }  
  
  
	//返回父节点的右孩子节点索引
    private int rightchild(int fpos) {  
        return 2 * fpos + 1;  
    }  
  
  
	//返回当前节点的父节点索引
    private int parent(int spos) {  
        return spos / 2;  
    }  
  
  	//判断当前节点是否为叶子节点
  	//父节点索引：1<=index<=size/2
  	//叶子节点索引：size/2<index<=size
    private boolean isleaf(int pos) {  
        return ((pos > size / 2) && (pos <= size));  
    }  
  
  	//交换两个节点的值
    private void swap(int pos1, int pos2) {  
        int tmp;  
        tmp = Heap[pos1];  
        Heap[pos1] = Heap[pos2];  
        Heap[pos2] = tmp;  
    }  
  
  
	//插入元素
    public void insert(int elem) {  
        size++;  
        Heap[size] = elem;  
        int current = size;  
		//若当前节点值小于父节点，即交换
        while (Heap[current] < Heap[parent(current)]) {  
            swap(current, parent(current));
			//更改值过的父节点再次判断  
            current = parent(current);  
        }  
    }  
  
  	//打印堆元素
    public void print() {  
        int i;  
        for (i = 1; i <= size; i++)  
            System.out.println(Heap[i]+" ");  
    }  
  
  
	//从堆中移除最小值
    public int removemin() {  
		//将堆中最后一个节点值与根节点交换
        swap(1, size);  
        size--;  
        if (size != 0)  
            pushdown(1);  
        return Heap[size + 1];  
    }  
  
  	//最小化该节点
    private void pushdown(int position) {  
        int smallestchild;  
        while (!isleaf(position)) {  
            smallestchild = leftchild(position); 
			//寻找孩子节点中的较小值 
            if ((smallestchild < size)  
                    && (Heap[smallestchild] > Heap[smallestchild + 1]))  
                smallestchild = smallestchild + 1; 
			//如果父节点最小，直接返回 
            if (Heap[position] <= Heap[smallestchild])  
                return;  
			//否则交换位置
            swap(position, smallestchild);  
			//并且再次最小化更改过值的子节点
            position = smallestchild;  
        }  
    }  
  
  
    public static void main(String args[])  
    {  
        HeapMin hm = new HeapMin(15);  
        hm.insert(23);  
        hm.insert(2);  
        hm.insert(3);  
        hm.insert(4);  
        hm.insert(34);
		hm.insert(32);
		hm.insert(28);  
		
		for(int i=0;i<7;i++)
			System.out.print(hm.removemin()+" ");
          
    }  
}  
