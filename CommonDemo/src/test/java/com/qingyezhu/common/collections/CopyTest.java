package com.qingyezhu.common.collections;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingyezhu.common.model.Animal;

/**
 * 数组：基本数据类型时，就是值拷贝，其中对于不可变类时，可以认为是值拷贝，如String；<br/>
 * 类类型，则是引用拷贝<br/>
 * 考察的目的是为了Arrays.copyOf，修改获得的数组能否影响之前的<br/>
 * 若是值拷贝，则不会，若是引用则会<br/>
 * @author zhuwang208531
 *
 */
public class CopyTest {

	private static final Logger logger = LoggerFactory.getLogger(CopyTest.class);

	@Test
	public void testArr() {
		final int len = 3;
		Animal[] animalArr = new Animal[len];
		for (int i = 0; i < len; i++) {
			animalArr[i] = new Animal(i, "name-" + i);
		}

		// copy to change
		Animal[] newAnimalArr = Arrays.copyOf(animalArr, len);
		
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
}
