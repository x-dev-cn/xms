package cn.xdev.auth.authorization.model;

import java.util.ArrayList;
import java.util.List;

public class SysUserAppRolesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysUserAppRolesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(Integer value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(Integer value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(Integer value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<Integer> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andApp_idIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andApp_idIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andApp_idEqualTo(Integer value) {
            addCriterion("app_id =", value, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idNotEqualTo(Integer value) {
            addCriterion("app_id <>", value, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idGreaterThan(Integer value) {
            addCriterion("app_id >", value, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_id >=", value, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idLessThan(Integer value) {
            addCriterion("app_id <", value, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idLessThanOrEqualTo(Integer value) {
            addCriterion("app_id <=", value, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idIn(List<Integer> values) {
            addCriterion("app_id in", values, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idNotIn(List<Integer> values) {
            addCriterion("app_id not in", values, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idBetween(Integer value1, Integer value2) {
            addCriterion("app_id between", value1, value2, "app_id");
            return (Criteria) this;
        }

        public Criteria andApp_idNotBetween(Integer value1, Integer value2) {
            addCriterion("app_id not between", value1, value2, "app_id");
            return (Criteria) this;
        }

        public Criteria andRole_idsIsNull() {
            addCriterion("role_ids is null");
            return (Criteria) this;
        }

        public Criteria andRole_idsIsNotNull() {
            addCriterion("role_ids is not null");
            return (Criteria) this;
        }

        public Criteria andRole_idsEqualTo(String value) {
            addCriterion("role_ids =", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsNotEqualTo(String value) {
            addCriterion("role_ids <>", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsGreaterThan(String value) {
            addCriterion("role_ids >", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsGreaterThanOrEqualTo(String value) {
            addCriterion("role_ids >=", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsLessThan(String value) {
            addCriterion("role_ids <", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsLessThanOrEqualTo(String value) {
            addCriterion("role_ids <=", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsLike(String value) {
            addCriterion("role_ids like", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsNotLike(String value) {
            addCriterion("role_ids not like", value, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsIn(List<String> values) {
            addCriterion("role_ids in", values, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsNotIn(List<String> values) {
            addCriterion("role_ids not in", values, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsBetween(String value1, String value2) {
            addCriterion("role_ids between", value1, value2, "role_ids");
            return (Criteria) this;
        }

        public Criteria andRole_idsNotBetween(String value1, String value2) {
            addCriterion("role_ids not between", value1, value2, "role_ids");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}