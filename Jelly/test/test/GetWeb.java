package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetWeb
{
    private int webDepth = 2;// �������
    private int intThreadNum = 10;// �߳���
    private String strHomePage = "";// ��ҳ��ַ
    private String myDomain;// ����
    private String fPath = "web";// ������ҳ�ļ���Ŀ¼��
    private ArrayList<String> arrUrls = new ArrayList<String>();// �洢δ����URL
    private ArrayList<String> arrUrl = new ArrayList<String>();// �洢����URL����������
    private Hashtable<String, Integer> allUrls = new Hashtable<String, Integer>();// �洢����URL����ҳ��
    private Hashtable<String, Integer> deepUrls = new Hashtable<String, Integer>();// �洢����URL���
    private int intWebIndex = 0;// ��ҳ��Ӧ�ļ��±꣬��0��ʼ
    private String charset = "GB2312";
    private String report = "";
    private long startTime;
    private int webSuccessed = 0;
    private int webFailed = 0;

    public GetWeb(String s)
    {
        this.strHomePage = s;
    }

    public GetWeb(String s, int i)
    {
        this.strHomePage = s;
        this.webDepth = i;
    }

    public synchronized void addWebSuccessed()
    {
        webSuccessed++;
    }

    public synchronized void addWebFailed()
    {
        webFailed++;
    }

    public synchronized void addReport(String s)
    {
        try
        {
            report += s;
            PrintWriter pwReport = new PrintWriter(new FileOutputStream("report.txt"));
            pwReport.println(report);
            pwReport.close();
        }
        catch (Exception e)
        {
            System.out.println("��ɱ����ļ�ʧ��!");
        }
    }

    public synchronized String getAUrl()
    {
        String tmpAUrl = arrUrls.get(0);
        arrUrls.remove(0);
        return tmpAUrl;
    }

    public synchronized String getUrl()
    {
        String tmpUrl = arrUrl.get(0);
        arrUrl.remove(0);
        return tmpUrl;
    }

    public synchronized Integer getIntWebIndex()
    {
        intWebIndex++;
        return intWebIndex;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        GetWeb gw = new GetWeb("http://gd.qq.com/a/20160815/034289.htm?qq=0&pt_src=3&ADUIN=879667160&ADSESSION=1471223363&ADTAG=CLIENT.QQ.5479_.0&ADPUBNO=26582", 5);// ������ҳ���������
        gw.getWebByHomePage();

    }

    public void getWebByHomePage()
    {
        startTime = System.currentTimeMillis();
        this.myDomain = getDomain();
        if (myDomain == null)
        {
            System.out.println("Wrong input!");
            // System.exit(1);
            return;
        }
        System.out.println("Homepage = " + strHomePage);
        addReport("Homepage = " + strHomePage + "!\n");
        System.out.println("Domain = " + myDomain);
        addReport("Domain = " + myDomain + "!\n");
        arrUrls.add(strHomePage);
        arrUrl.add(strHomePage);
        allUrls.put(strHomePage, 0);
        deepUrls.put(strHomePage, 1);
        File fDir = new File(fPath);
        if (!fDir.exists())
        {
            fDir.mkdir();
        }
        System.out.println("Start!");
        this.addReport("Start!\n");
        String tmp = getAUrl();
        this.getWebByUrl(tmp, charset, allUrls.get(tmp) + "");
        int i = 0;
        for (i = 0; i < intThreadNum; i++)
        {
            new Thread(new Processer(this)).start();
        }
        while (true)
        {
            if (arrUrls.isEmpty() && Thread.activeCount() == 1)
            {
                long finishTime = System.currentTimeMillis();
                long costTime = finishTime - startTime;
                System.out.println("\n\n\n\n\nFinished!");
                addReport("\n\n\n\n\nFinished!\n");
                System.out.println("Start time = " + startTime + " " + "Finish time = " + finishTime + " "
                        + "Cost time = " + costTime + "ms");
                addReport("Start time = " + startTime + " " + "Finish time = " + finishTime + " " + "Cost time = "
                        + costTime + "ms" + "\n");
                System.out.println("Total url number = " + (webSuccessed + webFailed) + " Successed: " + webSuccessed
                        + " Failed: " + webFailed);
                addReport("Total url number = " + (webSuccessed + webFailed) + " Successed: " + webSuccessed
                        + " Failed: " + webFailed + "\n");

                String strIndex = "";
                String tmpUrl = "";
                while (!arrUrl.isEmpty())
                {
                    tmpUrl = getUrl();
                    strIndex += "Web depth:" + deepUrls.get(tmpUrl) + " Filepath: " + fPath + "/web"
                            + allUrls.get(tmpUrl) + ".htm" + " url:" + tmpUrl + "\n\n";
                }
                System.out.println(strIndex);
                try
                {
                    PrintWriter pwIndex = new PrintWriter(new FileOutputStream("fileindex.txt"));
                    pwIndex.println(strIndex);
                    pwIndex.close();
                }
                catch (Exception e)
                {
                    System.out.println("��������ļ�ʧ��!");
                }
                break;
            }
        }
    }

    public void getWebByUrl(String strUrl, String charset, String fileIndex)
    {
        try
        {
            // if(charset==null||"".equals(charset))charset="utf-8";
            System.out.println("Getting web by url: " + strUrl);
            addReport("Getting web by url: " + strUrl + "\n");
            URL url = new URL(strUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            InputStream is = null;
            is = url.openStream();

            String filePath = fPath + "/web" + fileIndex + ".htm";
            PrintWriter pw = null;
            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            pw = new PrintWriter(writer);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            String rLine = null;
            String tmp_rLine = null;
            while ((rLine = bReader.readLine()) != null)
            {
                tmp_rLine = rLine;
                int str_len = tmp_rLine.length();
                if (str_len > 0)
                {
                    sb.append("\n" + tmp_rLine);
                    pw.println(tmp_rLine);
                    pw.flush();
                    if (deepUrls.get(strUrl) < webDepth)
                        getUrlByString(tmp_rLine, strUrl);
                }
                tmp_rLine = null;
            }
            is.close();
            pw.close();
            System.out.println("Get web successfully! " + strUrl);
            addReport("Get web successfully! " + strUrl + "\n");
            addWebSuccessed();
        }
        catch (Exception e)
        {
            System.out.println("Get web failed! " + strUrl);
            addReport("Get web failed! " + strUrl + "\n");
            addWebFailed();
        }
    }

    public String getDomain()
    {
        String reg = "(?<=http\\://[a-zA-Z0-9]{0,100}[.]{0,1})[^.\\s]*?\\.(com|cn|net|org|biz|info|cc|tv)";
        Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strHomePage);
        boolean blnp = m.find();
        if (blnp == true)
        {
            return m.group(0);
        }
        return null;
    }

    public void getUrlByString(String inputArgs, String strUrl)
    {
        String tmpStr = inputArgs;
        String regUrl = "(?<=(href=)[\"]?[\']?)[http://][^\\s\"\'\\?]*(" + myDomain + ")[^\\s\"\'>]*";
        Pattern p = Pattern.compile(regUrl, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tmpStr);
        boolean blnp = m.find();
        // int i = 0;
        while (blnp == true)
        {
            if (!allUrls.containsKey(m.group(0)))
            {
                System.out.println("Find a new url,depth:" + (deepUrls.get(strUrl) + 1) + " " + m.group(0));
                addReport("Find a new url,depth:" + (deepUrls.get(strUrl) + 1) + " " + m.group(0) + "\n");
                arrUrls.add(m.group(0));
                arrUrl.add(m.group(0));
                allUrls.put(m.group(0), getIntWebIndex());
                deepUrls.put(m.group(0), (deepUrls.get(strUrl) + 1));
            }
            tmpStr = tmpStr.substring(m.end(), tmpStr.length());
            m = p.matcher(tmpStr);
            blnp = m.find();
        }
    }

    class Processer implements Runnable
    {
        GetWeb gw;

        public Processer(GetWeb g)
        {
            this.gw = g;
        }

        public void run()
        {
            // Thread.sleep(5000);
            while (!arrUrls.isEmpty())
            {
                String tmp = getAUrl();
                getWebByUrl(tmp, charset, allUrls.get(tmp) + "");
            }
        }
    }
}
