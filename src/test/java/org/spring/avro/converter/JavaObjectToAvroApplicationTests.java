package org.spring.avro.converter;

import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.avro.converter.pojo.UserPOJO;
import org.spring.avro.converter.service.base.AvroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JavaObjectToAvroApplication.class)
public class JavaObjectToAvroApplicationTests {

	@Autowired
	private AvroService avroService;
	
	@Test
	public void testConvertToAvro() {
		try {
			DataFileWriter<UserPOJO>  dataFileWriter = (DataFileWriter<UserPOJO>) avroService.getDataFileWriter(UserPOJO.class, "user.avro");
			avroService.writeToAvro(dataFileWriter, new UserPOJO("Rajesh", "Chejerla", "chraj.kool@gmail.com"), UserPOJO.class);
			avroService.writeToAvro(dataFileWriter, new UserPOJO("James", "Bind", "james.bond@gmail.lom"), UserPOJO.class);
			avroService.closeWriter(dataFileWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConvertFromAvro(){
		try {
			DataFileReader<UserPOJO> dataFileReader = (DataFileReader<UserPOJO>) avroService.getDataFileReader("user.avro", UserPOJO.class);
			UserPOJO userPOJO = null;
			while(dataFileReader.hasNext()){
				userPOJO = dataFileReader.next(userPOJO);
				System.out.println("Name:"+userPOJO.getFirstName()+"_"+userPOJO.getLastName());
			}
			avroService.closeDataFileReader(dataFileReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
