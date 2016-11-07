package com.qingyezhu.common.collections;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.common.model.Animal;
import com.qingyezhu.common.model.Zoo;

/**
 * 数组：基本数据类型时，就是值拷贝，其中对于不可变类时，可以认为是值拷贝，如String；<br/>
 * 类类型，则是引用拷贝<br/>
 * 考察的目的是为了Arrays.copyOf，修改获得的数组能否影响之前的<br/>
 * 若是值拷贝，则不会，若是引用则会<br/>
 * 
 * 	从源Copy一份目标，源与目标是不同的对象，这是公认的，对于对象中域是基本类型，包括是String类型的甚至是final类型不可以修改的类来说，其都可以认为是值拷贝，就是新建了一份（某些不可变类可不是这样，但不影响使用）；<br/>
 * 对于对象中域是引用类型时，则认为是引用拷贝，只要修改了这个域的内容，则源对象或目标对象都会发生同样的变化
 *  
 * 
 * @author zhuwang208531
 *
 */
public class CopyTest {

	private static final Logger logger = LoggerFactory.getLogger(CopyTest.class);

	@Test
	public void testAnimalArr() {
		final int len = 3;
		Animal[] animalArr = new Animal[len];
		for (int i = 0; i < len; i++) {
			animalArr[i] = new Animal(i, "name-" + i);
		}

		// copy to change
		Animal[] newAnimalArr = Arrays.copyOf(animalArr, len);

		logger.info("boolean={}", animalArr == newAnimalArr);
		logger.info("animalArr={}", Arrays.toString(animalArr));
		logger.info("newAnimalArr={}", Arrays.toString(newAnimalArr));

		print(animalArr, newAnimalArr);
		Animal tmpAnimal = null;
		try {
			tmpAnimal = (Animal) newAnimalArr[0].clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.info("change before tmpAnimal={}", tmpAnimal);

		Animal[] newAimalArr1 = new Animal[len];
		System.arraycopy(animalArr, 0, newAimalArr1, 0, len);
		print(newAimalArr1);

		newAnimalArr[0].setId(10);
		newAnimalArr[1].setName("lili");

		print(animalArr, newAnimalArr);

		logger.info("change after tmpAnimal={}", tmpAnimal);
		print(newAimalArr1);
	}

	private void print(Animal[] src, Animal[] dest) {
		logger.info("start------------");
		print(src);
		logger.info("------------------");
		print(dest);
		logger.info("end------------");
	}

	private void print(Object[] itemArr) {
		for (Object item : itemArr) {
			logger.info("item={}", item);
		}
	}

	@Test
	public void testCopyAnimal() {
		Animal animal1 = new Animal(123, "hello");
		Animal animal2 = null;
		try {
			animal2 = (Animal) animal1.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("animal1={}, animal2={}", animal1, animal2);
		animal1.setId(1111);
		animal2.setName("bugaosuni");
		logger.info("animal1={}, animal2={}", animal1, animal2);
	}

	@Test
	public void testCopyZoo() {
		Animal animal = new Animal(1234, "name-12345");
		Zoo zoo = new Zoo(3456, "zoo-1", animal);
		logger.info("before animal={}, zoo={}, boolean={}", new Object[] { animal, zoo, zoo.getAnimal() == animal });
		Zoo zoo1 = null;
		try {
			zoo1 = (Zoo) zoo.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zoo.setId(7890);
		zoo.setName("zoo-7890");
		animal.setId(1000);
		animal.setName("animal-1000");
		
		logger.info("after-1 zoo={}, boolean={}", new Object[] { zoo, zoo.getAnimal() == animal });
		logger.info("after-2 zoo1={}, boolean={}", new Object[] { zoo1, zoo1.getAnimal() == animal });
	}

	@Test
	public void testCopyZooArr() {
		final int len = 3;
		Zoo[] zooArr = new Zoo[len];
		for (int i = 0; i < len; i++) {
			zooArr[i] = new Zoo(i * 245 + 1456, "zoo-" + i, new Animal(i * 2 + 43, "animal" + i));
		}
		
		Zoo[] newZooArr = Arrays.copyOf(zooArr, len);
		logger.info("boolean={}", newZooArr == zooArr);
		logger.info("zooArr={}", Arrays.toString(zooArr));
		logger.info("newZooArr={}", Arrays.toString(newZooArr));
		
		Zoo zoo = null;
		try {
			zoo = (Zoo) newZooArr[0].clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("zoo={}", zoo);
	
		newZooArr[0].setId(99999);
		newZooArr[0].setName("zoo-9999");
//		newZooArr[0].setAnimal(new Animal(1111, "animal-1111"));

		newZooArr[0].getAnimal().setId(1234);
		newZooArr[0].getAnimal().setName("animal-1234");
		
		logger.info("change------------");
		logger.info("zoo={}", zoo);
		logger.info("zooArr={}", Arrays.toString(zooArr));
		logger.info("newZooArr={}", Arrays.toString(newZooArr));		
	}
	
	@Test
	public void testCopyDeepZoo(){
		Animal animal = new Animal(1234, "name-12345");
		Zoo zoo = new Zoo(3456, "zoo-1", animal);
		logger.info("before animal={}, zoo={}, boolean={}", new Object[] { animal, zoo, zoo.getAnimal() == animal });
		Zoo zoo1 = null;
		try {
			zoo1 = (Zoo) zoo.deepClone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zoo.setId(7890);
		zoo.setName("zoo-7890");
		animal.setId(1000);
		animal.setName("animal-1000");
		
		logger.info("after-1 zoo={}, boolean={}", new Object[] { zoo, zoo.getAnimal() == animal });
		logger.info("after-2 zoo1={}, boolean={}", new Object[] { zoo1, zoo1.getAnimal() == animal });
	}
}
