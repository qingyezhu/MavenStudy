package com.qingyezhu.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qingyezhu.common.model.Animal;
import com.qingyezhu.common.model.Cat;

public class GenegicTest {

	@Test
	public void testSuper(){
		//实现类，可以是Number或Number的父类和接口，默认是Number类型，
		//添加类，必须是实现或继承了Number，这样就是父类的引用指向了子类对象，即多态的实现
		List<? super Number> numList1 = new ArrayList<>();
		numList1.add(1);
		numList1.add(12L);
		
		List<? super Number> numList2 = new ArrayList<Object>();
		numList2.add(11);
		numList2.add(111L);
		
		List<? super Number> numList3 = new ArrayList<Serializable>();
		
//		List<? super Number> numList4 = new ArrayList<Integer>();
	}
	
	@Test
	public void testExtends(){
		//实现类，可以是Number或Number的子类，如Byte、Integer、Double，默认是Number
		//由于添加时，并不知道实际类型是什么，且其实现类是其子类，且子类繁多，<br/>
		//假如添加的是Integer，从添加add方法来看是没错的 ，是继承自Number;<br/>
		//但是若其实现是Double，从实现角度来看，也没有错，<br/>
		//不过综合添加和实现，则有了问题，添加的是Integer，而底层存储的却是Double，这样就有问题了；<br/>
		//故最终，这种就不能添加<br/>
		
		//当然也可以直接在新建的时候，进行实例初始化添加，也没有问题，因为此时知道其需要添加的实际类型是什么，<br/>
		//故此时可以是当前实现类型或其子类型，又是多态的实现<br/>
		List<? extends Number> numList1 = new ArrayList<>();
		List<? extends Number> numList2 = new ArrayList<Byte>(){
			{
				add((byte)1);
			}
		};
		List<? extends Number> numList3 = new ArrayList<Integer>(){
			{
				add(12);
			}
		};
		List<? extends Number> numList4= new ArrayList<Double>(){
			{
				add(1.2);
			}
		};

		numList1.add(null);
		
		
		
		List<? extends Animal> animalList = new ArrayList<Animal>(){
			{
				add(new Animal(1, "animal"));
				add(new Cat(1, "cat", "bugaosu"));
			}
		};
		System.out.println(animalList);
	}
}
