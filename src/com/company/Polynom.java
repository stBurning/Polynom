package com.company;

import java.util.Arrays;

public class Polynom implements Comparable<Polynom>{

    public static final double ZERO = 1e-10;
    /** Коэффициенты полинома **/
    protected double[] a;
    /**
     * Конструктор по умолчанию:
     * - полином нулевой степени с коэффициентом 0
     **/
    public Polynom() {
        a = new double[]{0};
    }
    /** @param a - коэффициенты полинома */
    public Polynom(double[] a) {
        this.a = a;
        valid();
    }
    /**
     * Получение степени полинома
     * @return степень полинома
     */
    int getPower(){
        return a.length - 1;
    }

    private void valid(){
        int newLength = a.length;
        while (Math.abs(a[newLength - 1])<ZERO && newLength > 1)
            newLength--;

        double[] c = new double[newLength];
        System.arraycopy(a,0,c,0, newLength);
        this.a = c;
    }
    /**
     * Сложение двух полиномов
     * @param other - другой полином
     */
    public Polynom plus(Polynom other){
        Polynom minP, maxP;
        if (this.compareTo(other) > 0){ maxP = this; minP = other;
        }else { minP = this; maxP = other; }
        double[] c = new double[maxP.a.length];
        for (int i = 0; i < maxP.a.length; i++) {
            if (i < minP.a.length)
                c[i] = maxP.a[i] + minP.a[i];
        }

        return new Polynom(c);
    }
    /**
     * Умножение полинома на число
     * @param k - вещественный множитель
     */
    public Polynom times(double k){
        double[] c = a.clone();
        for (int i = 0; i < a.length; i++)
            c[i] *= k;
        return new Polynom(c);
    }
    /**
     * Деление полинома на число
     * @param k - вещественный ненулевой делитель
     */
    public Polynom div(double k) throws Exception {
        if (Math.abs(k) < ZERO)
            throw new Exception("Деление на 0.");

        return times(1/k);
    }
    /**
     * Разность полиномов
     * @param other - вычитаемый полином
     */
    public Polynom minus(Polynom other){
        return this.plus(other.times(-1));
    }

    /**
     * Произведение полиномов
     * @param other - другой полином
     */
    public Polynom mul(Polynom other){
        Polynom minP, maxP;
        if (this.compareTo(other) > 0){ maxP = this; minP = other;
        }else { minP = this; maxP = other; }

        double[] c = new double[minP.a.length];
        for (int i = 0; i < minP.a.length; i++) {
            c[i] = maxP.a[i] * minP.a[i];
        }
        return new Polynom(c);
    }
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = getPower(); i >= 0; i--) {
            if (!isZero(a[i])){
                if(i == getPower()) out.append((a[i] >= 0 ? "" : "-"));
                else out.append((a[i] >= 0 ? "+ " : "- "));
                if(Math.abs(a[i]) != 1 || i == 0) out.append(Math.abs((long) a[i]));
                if(i != 0) out.append("x");
                if(i > 1) out.append("^[").append(i).append("]");
                out.append(" ");
            }
//            if (!isZero(a[i])) {
//                if (i == 0) {
//                    if (i != getPower())
//                        out.append((a[i] >= 0 ? "+ " : "- "));
//                    else
//                        out.append((a[i] >= 0 ? "" : "-"));
//                    out.append(Math.abs(a[i]));
//                } else if (i == getPower()) {
//                    out.append((a[i] >= 0 ? "" : "-"));
//                    if (Math.abs(a[i]) != 1)
//                        out.append(Math.abs(a[i]));
//                    out.append("x^(").append(i).append(") ");
//                } else {
//                    out.append((a[i] >= 0 ? "+ " : "- "));
//                    if (Math.abs(a[i]) != 1)
//                        out.append(Math.abs(a[i]));
//                    out.append("x^(").append(i).append(") ");
//                }
//            }else out.append(a[getPower()]);
        }
        return out.toString();
    }
    public double invoke(double x){
        double result = 0;
        for (int i = 1; i < a.length; i++) {
            result += a[i] * Math.pow(x, i);
        }
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynom polynom = (Polynom) o;
        return Arrays.equals(a, polynom.a);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }

    @Override
    public int compareTo(Polynom o) {

        if (this.a.length > o.a.length){
            return 1;
        }else if(this.a.length < o.a.length){
            return -1;
        }else {
            for (int i = 0; i < a.length; i++) {
                //TODO double comparison
                if (a[i] > o.a[i]) return 1;
                if (a[i] < o.a[i]) return -1;
            }
        } return 0;
    }

    private boolean isZero(double x){
        return Math.abs(x) <= 2 * Double.MIN_VALUE;
    }
}
