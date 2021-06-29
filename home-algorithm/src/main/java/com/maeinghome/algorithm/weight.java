package com.maeinghome.algorithm;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class weight {

    public static void main(String[] args) {
        /*Double g= 2.0/3;
        BigDecimal bigG=new BigDecimal(g).setScale(1, BigDecimal.ROUND_HALF_UP); //期望得到12.4
        BigDecimal bigGS=new BigDecimal(g.toString()).setScale(1, BigDecimal.ROUND_HALF_UP); //期望得到12.4
        System.out.println("test        G:"+bigG.doubleValue());
        System.out.println("test String G:"+bigGS.doubleValue());*/
        final int MAXIMUM_CAPACITY = 1 << 30;
        Integer n = 161111111 - 1;
        System.out.println("args = " + Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println("args = " + Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println("args = " + Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println("args = " + Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println("args = " + Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println("args = " + Integer.toBinaryString(n));
        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);

        int a,b;
        a = 10;b=-11;
        System.out.println((a >> 31) ^ (b >> 31));
        Double[] w = {0.1,0.4,0.5};
        weight weight = new weight();
        Double u,s,l;
        Boolean uB,sB,lB;
        u = null;
        s = 122d;
        l = 10d;
        uB= true;
        sB= false;
        lB= true;
        Double score = weight.calculateWeightScore(u, uB, s, sB, l, lB, w);
        Double scoreQuick = weight.calculateWeightScoreQuick(u, uB, s, sB, l, lB, w);

        System.out.println("score       = " + score);
        System.out.println("scoreQuick  = " + scoreQuick);
    }

    private Double divide(Double dividend, Double divisor) {
        if (dividend == null) {
            dividend = 0d;
        }
        if (divisor == null || divisor == 0) {
            divisor = 1d;
        }
        BigDecimal b = new BigDecimal(dividend.toString());
        BigDecimal c = new BigDecimal(divisor.toString());
        BigDecimal divide = b.divide(c, 5, BigDecimal.ROUND_HALF_UP);
        return divide.doubleValue();
    }

    private Double[] initParam(Double u,Boolean isU, Double s, Boolean isS, Double l, Boolean isL, Double[] w){
        Double[] weights = new Double[3];
        Double zeroW = 0.0;
        weights[0] = isU ? w[0] : 0*(zeroW +=w[0]);
        weights[1] = isS ? w[1] : 0*(zeroW +=w[1]);
        weights[2] = isL ? w[2] : 0*(zeroW +=w[2]);
        if(zeroW.compareTo(0d) > 0) {
            weights[0] = isU ? zeroW * (this.divide(weights[0], 1.0 - zeroW)) + weights[0] : 0.0;
            weights[1] = isS ? zeroW * (this.divide(weights[1], 1.0 - zeroW)) + weights[1] : 0.0;
            weights[2] = isL ? zeroW * (this.divide(weights[2], 1.0 - zeroW)) + weights[2] : 0.0;
        }
        return weights;
    }


    private Double calculateWeightScoreQuick(Double u,Boolean isU, Double s, Boolean isS, Double l, Boolean isL, Double[] weightsO) {
        s = Optional.ofNullable(s).orElse(0d);
        u = Optional.ofNullable(u).orElse(0d);
        l = Optional.ofNullable(l).orElse(0d);
        Double[] weights = initParam( u, isU,  s,  isS,  l,  isL,  weightsO);
        Double d = u * weights[0] + s * weights[1] + l * weights[2];
        BigDecimal bigDecimal = new BigDecimal(d.toString());
        return bigDecimal.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 根据权重算总得分
     *
     * @param u			上级
     * @param isU		上级是否参与权重
     * @param s			同级
     * @param isS		同级是否参与权重
     * @param l			下级
     * @param isL		下级是否参与权重
     * @param weightsO	getWeight 方法
     * @author dema
     * @return
     */
    private Double calculateWeightScore(Double u,Boolean isU, Double s, Boolean isS, Double l, Boolean isL, Double[] weightsO) {
        if (s == null) {
            s = 0d;
        }
        if (u == null) {
            u = 0d;
        }
        if (l == null) {
            l = 0d;
        }
        Double[] weights = new Double[3];
        weights[0] = weightsO[0];
        weights[1] = weightsO[1];
        weights[2] = weightsO[2];
        Boolean[] counts = {isU, isS, isL};
        if (!isU || !isS || !isL) {
            int count = 0; // 为零数
            int postion = 0; // 为零的位置
            for (int i = 0; i < counts.length; i++) {
                Boolean isCount = counts[i];
                if (!isCount) {
                    count++;
                    postion += i;
                }
            }
            if (count == 3) {
                return -9999d;
            }
            if (count == 2) {
                if (postion == 1) {
                    weights[0] = 0d;
                    weights[1] = 0d;
                    weights[2] = 1d;
                } else if (postion == 2) {
                    weights[0] = 0d;
                    weights[1] = 1d;
                    weights[2] = 0d;
                } else if (postion == 3) {
                    weights[0] = 1d;
                    weights[1] = 0d;
                    weights[2] = 0d;
                }
            } else if (count == 1) {
                if (postion == 0) {
                    //weights[1] = weights[1] / (weights[1] + weights[2]);
                    weights[1] = this.divide(weights[1], weights[1] + weights[2]);
                    weights[2] = 1 - weights[1];
                    weights[0] = 0d;
                } else if (postion == 1) {
                    //weights[0] = weights[0] / (weights[0] + weights[2]);
                    weights[0] = this.divide(weights[0],weights[0] + weights[2]);
                    weights[2] = 1 - weights[0];
                    weights[1] = 0d;
                } else if (postion == 2) {
                    //weights[1] = weights[1] / (weights[1] + weights[0]);
                    weights[1] =this.divide(weights[1], weights[1] + weights[0]);

                    weights[0] = 1 - weights[1];
                    weights[2] = 0d;
                }
            }
        }
        //先算，然后在保留5位
        Double d = u * weights[0] + s * weights[1] + l * weights[2];
        BigDecimal bigDecimal = new BigDecimal(d.toString());
        return bigDecimal.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
