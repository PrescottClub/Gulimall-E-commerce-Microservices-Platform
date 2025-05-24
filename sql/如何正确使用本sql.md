# Gulimall SQL Files Usage Guide

**Project:** Gulimall E-commerce Microservices Platform  
**Maintained by:** Terence Qin  
**Repository:** https://github.com/PrescottClub/gilimall

## About These SQL Files

These SQL files have been carefully organized and optimized for the Gulimall e-commerce microservices platform. They provide the complete database schema and initial data needed to run the application.

## Database Structure

The SQL files create the following databases:
- `gulimall_admin` - Admin management system
- `gulimall_pms` - Product Management Service
- `gulimall_oms` - Order Management Service  
- `gulimall_ums` - User Management Service
- `gulimall_sms` - SMS and Coupon Service
- `gulimall_wms` - Warehouse Management Service

## How to Use

1. **Import SQL Files**: Execute each SQL file in your MySQL database
2. **Update Configuration**: Modify the `application.yml` files in each microservice to match your database configuration
3. **Database Naming**: The database names follow the pattern `gulimall_xxx` format

## Configuration Example

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/gulimall_pms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: your_username
    password: your_password
```

## Important Notes

1. **Attribute Value Type**: Add `value_type` field (tinyint) to `pms_attr` table if needed
2. **Entity Updates**: Update `AttrEntity.java` and `AttrVo.java` with `private Integer valueType`
3. **Mapper Configuration**: Add `<result property="valueType" column="value_type"/>` to `AttrDao.xml`

## Support

If you encounter any issues, please check the project documentation or create an issue in the GitHub repository.

**Terence Qin** - Project Maintainer

