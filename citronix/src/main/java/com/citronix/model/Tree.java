    package com.citronix.model;

    import java.time.LocalDate;

    import com.citronix.model.entity.BaseEntity;
    import com.citronix.model.enums.Level;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import jakarta.persistence.Transient;

    /**
     * Represents a Tree entity in the application.
     */
    @Table(name = "trees")
    @Entity
    public class Tree extends BaseEntity {

        @Column(nullable = false)
        private LocalDate plantedAt;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Level level;

        @ManyToOne(optional = false)
        @JoinColumn(name = "field_id", nullable = false)
        private Field field;

        @Transient
        public int calculateAge() {
            return LocalDate.now().getYear() - plantedAt.getYear();
        }

        public Tree() {
        }

        public Tree(LocalDate plantedAt, Level level, Field field) {
            this.plantedAt = plantedAt;
            this.level = level;
            this.field = field;
        }

        public LocalDate getPlantedAt() {
            return plantedAt;
        }

        public void setPlantedAt(LocalDate plantedAt) {
            this.plantedAt = plantedAt;
        }

        public Level getLevel() {
            return level;
        }

        public void setLevel(Level level) {
            this.level = level;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }
    }
