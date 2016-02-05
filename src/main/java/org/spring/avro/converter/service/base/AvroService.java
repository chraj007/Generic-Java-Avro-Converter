package org.spring.avro.converter.service.base;

import java.io.IOException;
import java.io.Serializable;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;

/**
 * The Interface AvroService.
 * 
 * @author Rajesh Chejerla
 */
public interface AvroService {
	
	/**
	 * Gets the data file writer.
	 *
	 * @param theClass the the class
	 * @param fileName the file name
	 * @return the data file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DataFileWriter<? extends Serializable> getDataFileWriter(Class<? extends Serializable> theClass, String fileName) throws IOException;
	
	/**
	 * Close writer.
	 *
	 * @param dataFileWriter the data file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void closeWriter(DataFileWriter<? extends Serializable> dataFileWriter) throws IOException;
	
	/**
	 * Write to avro.
	 *
	 * @param dataFileWriter the data file writer
	 * @param object the object
	 * @param theClass the the class
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeToAvro(DataFileWriter dataFileWriter, Object object, Class<? extends Serializable> theClass) throws IOException;
	
	/**
	 * Gets the data file reader.
	 *
	 * @param fileName the file name
	 * @param theClass the the class
	 * @return the data file reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DataFileReader<? extends Serializable> getDataFileReader(String fileName,Class<? extends Serializable> theClass)  throws IOException ;
	
	/**
	 * Close data file reader.
	 *
	 * @param dataFileReader the data file reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void closeDataFileReader(DataFileReader<? extends Serializable> dataFileReader)  throws IOException;
}
