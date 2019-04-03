import com.feihua.framework.base.modules.oss.cloud.api.ApiCloudStorageService;
import com.feihua.framework.base.modules.user.po.BaseUserPo;
import com.feihua.framework.mybatis.orm.mapper.NativeSqlMapper;
import com.feihua.utils.graphic.ImageUtils;
import com.feihua.utils.http.httpclient.HttpClientUtils;
import com.feihua.utils.io.StreamUtils;
import com.wwd.service.modules.wwd.api.ApiWwdUserPicPoService;
import com.wwd.service.modules.wwd.po.WwdUserPicPo;
import feihua.jdbc.api.pojo.BasePo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.csource.common.MyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2018/7/27 20:36
 */
public class Test {
    static ApiCloudStorageService apiCloudStorageService;
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApiWwdUserPicPoService apiWwdUserPicPoService = applicationContext.getBean(ApiWwdUserPicPoService.class);
        apiCloudStorageService = applicationContext.getBean(ApiCloudStorageService.class);
        NativeSqlMapper nativeSqlMapper = applicationContext.getBean(NativeSqlMapper.class);
        List<Map<String,Object>> list = nativeSqlMapper.selectByNativeSqlForList("select * from wwd_user_pic where pic_origin_url like '%doctor-patient%'");
        int i = 0;
        for (Map<String, Object> stringObjectMap : list) {
            String originUrl = stringObjectMap.get("pic_origin_url").toString();
            try {
                String newOriginUrl = downloadAndUpload1(originUrl);
                WwdUserPicPo wwdUserPicPo = new WwdUserPicPo();
                wwdUserPicPo.setId(stringObjectMap.get("id").toString());
                wwdUserPicPo.setPicOriginUrl(newOriginUrl);
                wwdUserPicPo.setPicThumbUrl(newOriginUrl);
                apiWwdUserPicPoService.updateByPrimaryKeySelective(wwdUserPicPo);
                System.out.println("*******************************" + (i++) + list.size());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static String downloadAndUpload(String path) throws IOException, MyException {
        HttpClient client = HttpClientUtils.getClient();
        HttpGet get = new HttpGet(path);

        HttpResponse httpResponse =  client.execute(get);
        InputStream inputStream = httpResponse.getEntity().getContent();

        String imageUrl = apiCloudStorageService.uploadSuffix(inputStream,"wwd", ".png");

        get.releaseConnection();
        return imageUrl;
    }
    public static String downloadAndUpload1(String path) throws IOException, MyException {
        URL url = new URL(path);
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        String imageUrl = apiCloudStorageService.uploadSuffix(dataInputStream,"wwd", ".png");

        return imageUrl;
    }
}
