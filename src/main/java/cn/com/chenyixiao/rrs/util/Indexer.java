package cn.com.chenyixiao.rrs.util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	

	private Directory directory;
	private Analyzer analyzer = 
			new SmartChineseAnalyzer(Version.LUCENE_31, true);
	private IndexWriterConfig indexWriterConfig;
	private IndexWriter indexWriter;
	
	
	public Indexer(String indexPath) throws IOException {
		directory = FSDirectory.open(new File(indexPath));
		indexWriterConfig = new IndexWriterConfig(Version.LUCENE_31, analyzer);
		indexWriter = new IndexWriter(directory, indexWriterConfig);
	}
	
	public void write(Document doc) throws CorruptIndexException, IOException {
		indexWriter.addDocument(doc);
	}
	
	public void close() throws CorruptIndexException, IOException {
		indexWriter.close();
		directory.close();
	}

	public Directory getDirectory() {
		return directory;
	}


	public void setDirectory(Directory directory) {
		this.directory = directory;
	}


	public Analyzer getAnalyzer() {
		return analyzer;
	}


	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}


	public IndexWriterConfig getIndexWriterConfig() {
		return indexWriterConfig;
	}


	public void setIndexWriterConfig(IndexWriterConfig indexWriterConfig) {
		this.indexWriterConfig = indexWriterConfig;
	}


	public IndexWriter getIndexWriter() {
		return indexWriter;
	}


	public void setIndexWriter(IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}
	
	

}
