package dessert.schedule;

/**
 * spring 定时任务
 * 
 * @author 严顺宽
 * @date 2016年3月2日 下午9:34:10
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dessert.service.MemberService;

@Component("changeStateTask")
public class ChangeStateTask {
	@Autowired
	private MemberService memberService;

	/**
	 * 定时更改会员状态
	 * 
	 * @description 0 0 0 * * ? 每天零点触发定时任务
	 * 
	 * @Scheduled 是spring提供的定时任务标签
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void doJob() {
//		memberService.deactivate();
//		System.out.println("check all members and update status");
	}

	/**
	 * 固定每分钟执行一次 ChangeStateTask.doJob1()
	 */
	@Scheduled(fixedRate = 1 * 1000)
	public void doJob1() {
		memberService.deactivate();
		// System.out.println(new Date() + "-----------------doJob1");
	}

	/**
	 * 上次任务结束后一分钟后再次执行
	 */
	@Scheduled(fixedDelay = 60 * 1000)
	public void doJob2() {
		// System.out.println(new Date() + "-----------------doJob2");
	}
}
