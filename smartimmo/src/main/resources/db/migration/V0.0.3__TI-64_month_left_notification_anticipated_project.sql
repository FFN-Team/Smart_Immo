-- Add column month_left_notification on potential_project
ALTER TABLE potential_project
    ADD COLUMN notification_date DATE;