package cn.lilq.springbootdemo.pojo;

import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 20:58
 * 热度类 用于描述商品的热度
 */
public class Fiery {
    private Long id;
    private String name;
    private Integer fiery;

    public Fiery() {
    }

    public Fiery(Long id, String name, Integer fiery) {
        this.id = id;
        this.name = name;
        this.fiery = fiery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFiery() {
        return fiery;
    }

    public void setFiery(Integer fiery) {
        this.fiery = fiery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fiery fiery1 = (Fiery) o;
        return Objects.equals(id, fiery1.id) &&
                Objects.equals(name, fiery1.name) &&
                Objects.equals(fiery, fiery1.fiery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fiery);
    }

    @Override
    public String toString() {
        return "Fiery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fiery=" + fiery +
                '}';
    }
}
