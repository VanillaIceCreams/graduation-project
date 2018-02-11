import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxj.mapper.TbItemMapper;
import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemExample;


public class TestPageHelper {
	@Test
	public void testPageHelper(){
		//获取spring上下文对象
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application*.xml");
		//获取TbItemMapper代理对象
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		//查询之前先调用分页语句
		PageHelper.startPage(1, 30);
		
		TbItemExample example = new TbItemExample();
		List<TbItem> list = mapper.selectByExample(example);
		PageInfo<TbItem> pi = new PageInfo<>(list);
		long total = pi.getTotal();
		System.out.println("总记录："+total+"总页数："+pi.getPages());
		
	}
}
