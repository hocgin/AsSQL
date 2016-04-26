## Assql
A Simple SQL tool

## Download

## Usage
```java
        Condition cnd = Cnd.where("name", ">", "xiaoming")
                .andIn("age", 12, 56, 89)
                .and(new Wrap("weight", ">", "60").orIn("height", 160, 170, 180))
                .groupBy("id")
                .having(Cnd.where("12", ">", "12"))
                .desc("age");

        String sql = String.format("%s from %s where %s", "select *", "tableName", cnd.toSql());
```

## Screenshot
![image](http://7xs6lq.com1.z0.glb.clouddn.com/AsSQL.png)