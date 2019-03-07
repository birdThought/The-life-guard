package com.lifeshs.service.tool.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.po.invitation.InvitationPO;
import com.lifeshs.service.tool.IInvitationCodeService;
import com.lifeshs.service1.data.invitation.InvitationService;

@Service(value = "invitationCodeService")
public class InvitationCodeServiceImpl implements IInvitationCodeService {

    @Resource(name = "invitationService")
    private InvitationService invitationService;
    
    private final String GENERATE_SOURCE = "rm8uz62dgktqc974g3nfwxpv5shyeba";
    
    @Override
    public String createCode() {
        String code = createCode(10, 2, 8, 5, GENERATE_SOURCE);
        InvitationPO invitation = new InvitationPO();
        invitation.setInvitationCode(code);
//        invitationService.addInvitation(invitation);
        return code;
    }

    @Override
    public List<String> createCodes(int size) {
        List<String> codes = new ArrayList<>();
        List<InvitationPO> invitationList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String code = createCode(10, 2, 8, 5, GENERATE_SOURCE);
            codes.add(code);
            InvitationPO invitation = new InvitationPO();
            invitation.setInvitationCode(code);
            invitationList.add(invitation);
        }
//        invitationService.addInvitation(invitationList);
        return codes;
    }
    
    /**
     *  生成一个数字字母组合的邀请码
     *  <p>该邀请码自带一个校验位
     *  @author yuhang.weng 
     *  @DateTime 2017年4月6日 下午4:15:32
     *
     *  @param length 邀请码长度
     *  @param referenceIndex 参考字段
     *  @param validIndex 校验字段
     *  @param deviation 偏移位
     *  @param source 带有排列顺序的一个数字字母字符串
     *  @return
     */
    private String createCode(int length, int referenceIndex, int validIndex, int deviation, String source) {
        if (length <= 0) {
            throw new IndexOutOfBoundsException("请确保length大于0");
        }
        if (referenceIndex > length) {
            throw new IndexOutOfBoundsException("请确保referenceIndex值小于等于length");
        }
        if (referenceIndex >= validIndex) {
            throw new IndexOutOfBoundsException("请确保referenceIndex小于length");
        }
        if (validIndex > length) {
            throw new IndexOutOfBoundsException("请确保validIndex值小于等于length");
        }
        /*System.out.println("带有排列顺序的一个数字字母字符串:" + source);
        System.out.println("邀请码长度:" + length);
        System.out.println("参考字段:" + referenceIndex);
        System.out.println("校验字段:" + validIndex);
        System.out.println("偏移位:" + deviation);*/
        
        LinkedList<Character> sourceList = new LinkedList<>();
        for (Character s : source.toCharArray()) {
            sourceList.add(s);
        }
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int codeLength = code.length() + 1;
            if (codeLength != validIndex + 1) {
                int index = (int) Math.floor(ThreadLocalRandom.current().nextDouble() * source.length());
                String nowStr = String.valueOf(source.charAt(index));
                code.append(nowStr);
                source = source.replaceAll(nowStr, "");
            } else {
                Character character = code.charAt(referenceIndex);
                int newindex = sourceList.indexOf(character) + deviation;
                if (newindex > sourceList.size() - 1) {
                    newindex %= (sourceList.size());
                }
                String nowStr = String.valueOf(sourceList.get(newindex));
                code.append(nowStr);
            }
        }
        return code.toString();
    }
}
