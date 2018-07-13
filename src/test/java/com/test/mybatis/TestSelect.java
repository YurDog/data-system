package com.test.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.data.system.dao.RoleDAO;
import com.data.system.po.Role;
import com.data.system.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring-mybatis.xml")
public class TestSelect {

	Logger log = LoggerFactory.getLogger(TestSelect.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleDAO roleDAO;

	@Test
	@Transactional
	public void testFindUserNameById() {
		String userName1 = roleService.getUserName(251682);
		log.error("sql result:" + userName1);
		String userName2 = roleService.getUserName(251682);
		log.error("sql result:" + userName2);
		String userName3 = roleService.getUserName(251682);
		log.error("sql result:" + userName3);
		String userName4 = roleService.getUserName(251682);
		log.error("sql result:" + userName4);
	}

	@Test
	public void testFindRolesByIds() {
		int [] ids = {123,34234};
		List<Role> roles = roleDAO.findRolesByIds(ids);
		for(Role role:roles) {
			log.info(role.toString());
		}
	}
	
//	@Test
	public void testForEachInsert() {
		for (int i = 0; i < 1000000; i++) {
			Role role = new Role();
			role.setUserName(TestSelect.getChineseName());
			role.setAge(i%10+10);
			role.setBirthday(new Date());
			role.setMoney(i%100+i%10 + 159);
			roleDAO.inserRole(role);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static String firstName = "ÕÔÇ®ËïÀîÖÜÎâÖ£Íõ·ë³ÂñÒÎÀ½¯Éòº«ÑîÖìÇØÓÈÐíºÎÂÀÊ©ÕÅ¿×²ÜÑÏ»ª½ðÎºÌÕ½ªÆÝÐ»×ÞÓ÷°ØË®ñ¼ÕÂÔÆËÕÅË¸ðÞÉ·¶ÅíÀÉÂ³Î¤²ýÂíÃç·ï»¨·½ÓáÈÎÔ¬ÁøÛº±«Ê·ÌÆ·ÑÁ®á¯Ñ¦À×ºØÄßÌÀëøÒóÂÞ±ÏºÂÚù°²³£ÀÖÓÚÊ±¸µÆ¤±åÆë¿µÎéÓàÔª²·¹ËÃÏÆ½»ÆºÍÄÂÏôÒüÒ¦ÉÛÕ¿ÍôÆîÃ«ÓíµÒÃ×±´Ã÷ê°¼Æ·ü³É´÷Ì¸ËÎÃ©ÅÓÐÜ¼ÍÊæÇüÏî×£¶­Áº¶ÅÈîÀ¶ãÉÏ¯¼¾ÂéÇ¿¼ÖÂ·Â¦Î£½­Í¯ÑÕ¹ùÃ·Ê¢ÁÖµóÖÓÐìÇñÂæ¸ßÏÄ²ÌÌï·®ºúÁè»ôÓÝÍòÖ§¿Â¾Ì¹ÜÂ¬Äª¾­·¿ôÃçÑ¸É½âÓ¦×ÚÐû¶¡êÚµËÓôµ¥º¼ºé°üÖî×óÊ¯´Þ¼ªÅ¥¹¨³ÌïúÐÏ»¬ÅáÂ½ÈÙÎÌÜ÷Ñòì¶»ÝÕçÎº¼Ó·âÜÇôà´¢½ù¼³ÚûÃÓËÉ¾®¶Î¸»Î×ÎÚ½¹°Í¹­ÄÁÚóÉ½¹È³µºîåµÅîÈ«Û­°àÑöÇïÖÙÒÁ¹¬Äþ³ðèï±©¸Êî×À÷ÈÖ×æÎä·ûÁõ½ªÕ²ÊøÁúÒ¶ÐÒË¾ÉØÛ¬Àè¼»±¡Ó¡ËÞ°×»³ÆÑÌ¨´Ó¶õË÷ÏÌ¼®Àµ×¿ÝþÍÀÃÉ³ØÇÇÒõÓôñãÄÜ²ÔË«ÎÅÝ·µ³µÔÌ·¹±ÀÍåÌ¼§Éê·ö¶ÂÈ½Ô×ÛªÓºÈ´è³É£¹ðå§Å£ÊÙÍ¨±ßìèÑà¼½Û£ÆÖÉÐÅ©ÎÂ±ð×¯êÌ²ñöÄÑÖ³äÄ½Á¬ÈãÏ°»Â°¬ÓãÈÝÏò¹ÅÒ×É÷¸êÁÎ¸ýÖÕôß¾Óºâ²½¶¼¹¢Âúºë¿ï¹úÎÄ¿Ü¹ãÂ»ãÚ¶«Å¹ì¯ÎÖÀûÎµÔ½ÙçÂ¡Ê¦¹®ØÇÄôêË¹´°½ÈÚÀäö¤ÐÁãÛÄÇ¼òÈÄ¿ÕÔøÎãÉ³Ø¿Ñø¾ÏÐë·á³²¹ØØáÏà²éºó½­ºìÓÎóÃÈ¨åÖ¸ÇÒæ»¸¹«ÍòÙ¹Ë¾ÂíÉÏ¹ÙÅ·ÑôÏÄºîÖî¸ðÎÅÈË¶«·½ºÕÁ¬»Ê¸¦Î¾³Ù¹«Ñòå£Ì¨¹«Ò±×ÚÕþå§Ñô´¾ÓÚÖÙËïÌ«ÊåÉêÍÀ¹«ËïÀÖÕýÐùÔ¯ÁîºüÖÓÀëãÌÇð³¤ËïÄ½ÈÝÏÊÓÚÓîÎÄË¾Í½Ë¾¿ÕØÁ¹ÙË¾¿ÜØë¶½×Ó³µò§Ëï¶ËÄ¾Î×Âí¹«Î÷ÆáµñÀÖÕýÈÀæá¹«Á¼ÍØ°Î¼Ð¹ÈÔ×¸¸¹ÈÁ»½ú³þÑÖ·¨ÈêÛ³Í¿ÇÕ¶Î¸É°ÙÀï¶«¹ùÄÏÃÅºôÑÓ¹éº£ÑòÉàÎ¢ÉúÔÀË§çÃ¿º¿öºóÓÐÇÙÁºÇð×óÇð¶«ÃÅÎ÷ÃÅÉÌÄ²ÙÜÙ¦²®ÉÍÄÏ¹¬Ä«¹þÚÛóÎÄê°®ÑôÙ¡µÚÎåÑÔ¸£°Ù¼ÒÐÕÐø";
	private static String girl = "Ðã¾êÓ¢»ª»ÛÇÉÃÀÄÈ¾²Êç»ÝÖé´äÑÅÖ¥ÓñÆ¼ºì¶ðÁá·Ò·¼Ñà²Ê´º¾ÕÀ¼·ï½àÃ·ÁÕËØÔÆÁ«Õæ»·Ñ©ÈÙ°®ÃÃÏ¼ÏãÔÂÝºæÂÑÞÈð·²¼Ñ¼ÎÇíÇÚÕäÕêÀò¹ðæ·Ò¶èµè´æ«çù¾§åûÜçÇïÉºÉ¯½õ÷ìÇàÙ»æÃæ¯ÍñæµèªÓ±Â¶Ñþâùæ¿ÑãÝíæýÒÇºÉµ¤ÈØÃ¼¾ýÇÙÈïÞ±Ý¼ÃÎá°Ô·æ¼Ü°è¥çüÔÏÈÚÔ°ÒÕÓ½Çä´ÏÀ½´¿Ø¹ÔÃÕÑ±ùË¬çþÜøÓðÏ£ÄþÐÀÆ®ÓýäÞð¥óÞÈáÖñö°ÄýÏþ»¶Ïö·ãÜ¿·Æº®ÒÁÑÇÒË¿É¼§ÊæÓ°ÀóÖ¦Ë¼Àö ";
	private static String boy = "Î°¸ÕÓÂÒã¿¡·åÇ¿¾üÆ½±£¶«ÎÄ»ÔÁ¦Ã÷ÓÀ½¡ÊÀ¹ãÖ¾ÒåÐËÁ¼º£É½ÈÊ²¨Äþ¹ó¸£ÉúÁúÔªÈ«¹úÊ¤Ñ§Ïé²Å·¢ÎäÐÂÀûÇå·É±ò¸»Ë³ÐÅ×Ó½ÜÌÎ²ý³É¿µÐÇ¹âÌì´ï°²ÑÒÖÐÃ¯½øÁÖÓÐ¼áºÍ±ë²©³ÏÏÈ¾´ÕðÕñ×³»áË¼ÈººÀÐÄ°î³ÐÀÖÉÜ¹¦ËÉÉÆºñÇìÀÚÃñÓÑÔ£ºÓÕÜ½­³¬ºÆÁÁÕþÇ«ºàÆæ¹ÌÖ®ÂÖº²ÀÊ²®ºêÑÔÈôÃùÅó±óÁº¶°Î¬Æô¿ËÂ×ÏèÐñÅôÔó³¿³½Ê¿ÒÔ½¨¼ÒÖÂÊ÷Ñ×µÂÐÐÊ±Ì©Ê¢ÐÛè¡¾û¹Ú²ßÌÚéªéÅ·çº½ºë";

	public static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 * ·µ»ØÖÐÎÄÐÕÃû
	 */
	private static String name_sex = "";

	private static String getChineseName() {
		int index = getNum(0, firstName.length() - 1);
		String first = firstName.substring(index, index + 1);
		int sex = getNum(0, 1);
		String str = boy;
		int length = boy.length();
		if (sex == 0) {
			str = girl;
			length = girl.length();
			name_sex = "Å®";
		} else {
			name_sex = "ÄÐ";
		}
		index = getNum(0, length - 1);
		String second = str.substring(index, index + 1);
		int hasThird = getNum(0, 1);
		String third = "";
		if (hasThird == 1) {
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;
	}
}
