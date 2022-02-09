package com.thrift;
import org.apache.thrift.TException;
/**
 * @author yogo.wang
 * @date 2017/02/21-下午2:13.
 */
public class HelloServiceImpl implements Hello.Iface {
  public String helloString(String para) throws TException {
    return "result:" + para;
  }
}