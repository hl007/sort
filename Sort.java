import java.util.*;
public class Sort{
	public static void main(String[] args){
		int[] data = {34,23,14, 13, 11, 15, 12 };
		radixSort(data);		
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
		}
	}


	//选择排序——直接选择排序
	public static void straightSelectionSort(int[] data){
		//第一次从R[0]~R[n-1]中选取最小值，与R[0]交换
		//第二次从R[1]~R[n-1]中选取最小值，与R[1]交换
		//…
		//第i次从R[i-1]~R[n-1]中选取最小值，与R[i-1]交换
		//...
		//第n-1次从R[n-2]~R[n-1]中选取最小值，与R[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列
		for(int i=0;i<data.length-1;i++){
			int minIndex=i;
			for(int j=i+1;j<data.length;j++){
				if(data[j]<data[minIndex]){
					minIndex=j;
				}
			}

			if(minIndex!=i){
				int tmp=data[i];
            	data[i]=data[minIndex];
            	data[minIndex]=tmp;
			}
		}
	}

	//选择排序——堆排序
	public static void heapSort(int[] data){
		HeapMin heap=new HeapMin(data.length+1);
		for(int i=0;i<data.length;i++){			
			heap.insert(data[i]);		
		}	
		
		for(int i=0;i<data.length;i++){
			data[i]=heap.removemin();
		}
	}


	//交换排序——冒泡排序
	//比较相邻元素，如果第一个比第二个大，就交换他们两个。
	//对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
	//针对所有的元素重复以上的步骤，除了最后一个。
	//持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。 
	//在最坏的情况下n个元素的数组进行冒泡排序需要n-1趟比较
	public static void bubbleSort(int[] data){
		for(int i=0;i<data.length-1;i++){
			//标记是否发生交换
			boolean flag=false;
			for(int j=0;j<data.length-i-1;j++){
				if(data[j]>data[j+1]){
					int tmp=data[j];
					data[j]=data[j+1];
					data[j+1]=tmp;
				}
				flag=true;
			}

			//如果在某次循环中没有发生交换, 说明已排好序，可结束循环
			if(!flag){
				break;
			}
		}
	}


	//交换排序——快速排序，冒泡排序的改进
	private static void quickSort(int[] data,int start,int end){
		if(start<end){
			//随意选一个值作为基准值
			int key=data[start];
			//i从左边开始搜索，找出小于基准值的元素的索引
			int i=start;	
			//j从左边开始搜索，找出大于基准值的元素的索引
			int j=end+1;

			while(true){
				//循环直到左边找到大于基准值
				while(i<end && data[++i]<data[start]);
				//循环直到右边找到小于基准值
            	while(j>start && data[--j]>data[start]);
				//如果i<j,交换
				if(i<j){
					int tmp=data[i];
					data[i]=data[j];
					data[j]=tmp;
				}
				else{
					//i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束
					//此时i>=j
					//终止while循环
					break;
				}
			}
			
			//交换基准值和j(不能是i)
			int tmp=data[start];
			data[start]=data[j];
			data[j]=tmp;

			//递归左边
			quickSort(data,start,j-1);
			//递归右边
			quickSort(data,j+1,end);
		}
	}

	//插入排序——直接插入排序
	//每次从无序表中取出第一个元素，把它插入到有序表的合适位置，使有序表仍然有序。 
	//第一趟比较前两个数，然后把第二个数按大小插入到有序表中
	//第二趟把第三个数据与前两个数从后向前扫描，把第三个数按大小插入到有序表中；
	//依次进行下去，进行了(n-1)趟扫描以后就完成了整个排序过程。
	public static void straightInsertSort(int[] data){
        for(int i=1;i<data.length;i++){
			//使得有序表+待插入元素——>有序表 
			//从有序表末尾元素开始
			int tmp=data[i];
			int j;
			//有序表中元素<=插入元素，则结束循环
			for(j=i-1;j>=0 && data[j]>tmp;j--){
				//如果有序表中元素比插入元素大，就后移
				data[j+1]=data[j];	
			}
			
			//将插入元素放到小于它的元素后面一位
			data[j+1]=tmp;
		}
   
	}

	//插入排序——二分插入排序，直接插入排序的改进
	public static void binaryInsertSort(int[] data){
        for(int i=1;i<data.length;i++){
			int tmp=data[i];
			//直接从有序表的中间比
			int low = 0;
            int height = i-1;	
			while(low<=height){
				//中间值
				int mid=(low+height)/2;

				//插入值大于中间值
				if(tmp>data[mid]){
					//限制在大于中点搜索
					low=mid+1;	
				}
				else{
					//否则限制在小于中点搜索
					height=mid-1;
				}
			}
			
			//将low到i处的所有元素向后整体移一位
			for(int j=i;j>low;j--){
				data[j]=data[j-1];
			}
			
			//最后将插入值放入合适位置
			data[low]=tmp;
		}
	}

	
	//插入排序——希尔排序
	//先取一个正整数 d1(d1<n)，把全部记录分成d1个组，所有距离为d1的倍数的记录看成一组，然后在各组内进行直接插入排序
	//然后取d2(d2<d1)
	//重复上述分组和排序操作；直到取di=1(i>=1) 位置，即所有记录成为一个组，最后对这个组进行插入排序。一般选d1约为n/2，d2为d1/2，d3为d2/2，…，di=1
	public static void shellSort(int[] data){
		//初始，将数组分为d个组
		int gap=data.length/2; 
			
		while(gap>0){
			//对各组进行直接插入排序
			for(int i=gap;i<data.length;i++){
				
				//待插入元素
				int tmp=data[i];

				int j;
				//开始，如果有序表中尾元素大于插入值，将末尾元素后移；
				//当有序表中元素小于插入值时，结束循环
				for(j=i-gap;j>=0 && data[j]>tmp;j-=gap){
					data[j+gap]=data[j];
				}
	
				//将插入元素放到小于它的元素后面一位(gap长度)
				data[j+gap]=tmp;
			}
			
			//再缩短gap,直到gap=1为止
			gap=gap/2;
		}
	}


	//归并排序
	//分而治之
	//先将长度为n的无序序列看成是n个长度为1的有序子序列
	//然后两两合并，得到n/2个长度为2的有序子序列
	//再进行两两合并….不断重复，最终得到一个长度为n的有序序列
	public static void mergeSort(int[] data,int left,int right){
		//直到将序列分解到长度为1为止
		if(left>=right) return;
	
		//找出中间索引		
		int center=(left+right)/2;

		//将左边序列进行递归
		mergeSort(data,left,center);
		//将右边序列进行递归
		mergeSort(data,center+1,right);

		//合并
		merge(data,left,center,right);
				
	}	
	
	//合并两个有序序列为一个有序序列
	public static void merge(int[] data,int left,int center,int right){
	
		//临时数组	
		int[] tmpArr=new int[data.length];

		//右边序列的第一个元素索引
		int mid=center+1;

		//third记录tmpArr的索引
		int third=left;

		//缓存左边序列第一个元素的索引
		int tmp=left;

		while(left<=center && mid<=right){
			//分别从两序列左边开始移动，将两个序列中较小的放进临时数组
			if(data[left] <=data[mid]){
				tmpArr[third++]=data[left++];
			}
			else{
				tmpArr[third++]=data[mid++];
			}
		}

		//剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
		//右边序列剩余
		while(mid<=right){
			tmpArr[third++]=data[mid++];
		}

		//左边序列剩余
		while(left<=center){
			tmpArr[third++]=data[left++];
		}

		//将排好序的临时数组拷贝到原数组中
		while(tmp<=right){
			data[tmp]=tmpArr[tmp++];
		}
		
	}


	//桶排序
	//将序列分到有限数量的桶子里，每个桶子再进行排序（有可能再使用别的排序算法或是以递回方式继续使用桶排序进行排序）
	//桶排序要求数据的分布必须均匀
	public static void bucketsSort(int[] data){
		
		//序列中的最大值
		int max=data[0];
		
		//序列中的最小值
		int min=data[0];
 
		//得到序列中的最大值和最小值
    	for(int i=0;i<data.length;i++){
       		max=Math.max(max,data[i]);
        	min=Math.min(min,data[i]);
    	}
    
    	//桶数
    	int bucketNum=(max-min)/data.length+1;
  		
		//创建桶,元素也是一个数组列表
		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<ArrayList<Integer>>(bucketNum);

		//添加元素到桶中
	    for(int i=0;i<bucketNum;i++){
    	    bucketArr.add(new ArrayList<Integer>());
    	}
    
 		
		//将序列中的元素分到各个桶中
	    for(int i=0; i<data.length;i++){
			//确定桶的索引
    	    int num=(data[i]-min)/(data.length);
			//添加元素到对应桶的数组列表中
        	bucketArr.get(num).add(data[i]);
    	}
    
 		
		int index=0;
		//将每个桶中的元素再次排序
    	for(int i=0;i<bucketArr.size();i++){
			Collections.sort(bucketArr.get(i));
			
			//将排好的序列copy给原数组
			for(int j=0;j<bucketArr.get(i).size();j++){
				data[index++]=bucketArr.get(i).get(j);
			}
    	}	  		 
	}

	//基数排序
	//基数排序是一种分配式排序，即通过将所有数字分配到应在的位置最后再覆盖到原数组完成排序的过程。
	public static void radixSort(int[] data){
		//寻找最大值
		int max=data[0];
		for(int i=0;i<data.length;i++){
			if(data[i]>max){
				max=data[i];
			}
		}
		
		//获取最大位数
		int d=(max+"").length();

		int n=0; //代表位数


		//构建桶
		int[][] bucket=new int[10][data.length];
		
		//用于保存每个桶里有多少数字
		int[] order=new int[10];

		while(n<d){
			//将序列放入相应桶中
			for(int ele:data){
				int tmp=(int)Math.pow(10,n);
				int digit=(ele/tmp)%10;
				bucket[digit][order[digit]++]=ele;
			}
			
			//将生成桶里的数据覆盖到原数组
			int k=0;
			for(int i=0;i<bucket.length;i++){
				for(int j=0;j<order[i];j++){
					data[k++]=bucket[i][j];	
				}	
				//将桶里计数器置0，用于下一次排序
				order[i]=0;
			}
			n++;
		}
	}

}
