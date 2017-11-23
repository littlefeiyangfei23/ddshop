package com.qf.ddshop.lucene.manager;

import com.qf.ddshop.lucene.dao.IBookDao;
import com.qf.ddshop.lucene.impl.BookDaoImpl;
import com.qf.ddshop.lucene.po.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexManager {
    //创建索引
    @Test
    public void createIndex() throws IOException {
        //采集数据
        IBookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.listBooks();
        //将采集数据保存到文档域中
        List<Document> documentList = new ArrayList<Document>();

        for (Book book : bookList){
            Document document = new Document();
            //图书ID：不分词、索引、存储
            Field id = new StringField("id",book.getId().toString(), Field.Store.YES);
            //图书名称：分词、索引、存储
            Field name = new TextField("name",book.getName(), Field.Store.YES);
            //图书价格：不分词、索引、存储
            Field price = new FloatField("price",book.getPrice(),Field.Store.YES);
            //图片地址：不分词、不索引、存储
            Field pic = new StoredField("pic",book.getPic());
            //图片描述：分词、索引、不存储
            Field description = new TextField("description",book.getDescription(),Field.Store.NO);

            //将field添加到document中
            document.add(id);
            document.add(name);
            document.add(price);
            document.add(pic);
            document.add(description);
            //添加到文档域集合中
            documentList.add(document);
        }

        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        //指定lucene版本
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址
        File indexFile = new File("D:\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory,cfg);
        //创建索引，将每个文档域对象存放到索引库中
        for (Document doc : documentList) {
            writer.addDocument(doc);
        }
        //关闭连接
        writer.close();
    }

    //查询索引
    @Test
    public void indexSearch() throws ParseException, IOException {
        QueryParser parser = new QueryParser("description",new StandardAnalyzer());
        Query query = parser.parse("description:spring");
        //指定索引库的地址
        File indexFile = new File("D:\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        //搜索索引库的内容
        TopDocs topDocs = searcher.search(query, 10);
        System.out.println("匹配的记录总数为：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs){
            System.err.println("docId:" + scoreDoc.doc);
            Document document = searcher.doc(scoreDoc.doc);
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
            System.out.println(document.get("description"));

        }
    }
    //删除索引
    @Test
    public void indexDelete() throws IOException {
        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址
        File indexFile = new File("D:\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory,cfg);
        //根据条件删除.
        Term term = new Term("name","java");
        writer.deleteDocuments(term);
        //删除全部
        //writer.deleteAll();
        writer.close();
    }
    //更新索引
    @Test
    public void indexUpdate() throws IOException {
        //创建分词器，使用Lucene默认分词器
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址
        File indexFile = new File("D:\\bookindex");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory,cfg);
        //更新
        Term term = new Term("name","Spring");
        //更新模板
        Document doc = new Document();
        doc.add(new TextField("name","javaScript", Field.Store.YES));
        //如果存在，则更新
        //如果不存在，则新增
        writer.updateDocument(term,doc);
        writer.close();
    }
}
