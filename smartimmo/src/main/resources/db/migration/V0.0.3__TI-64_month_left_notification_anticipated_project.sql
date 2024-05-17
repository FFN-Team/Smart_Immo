-- Add column month_left_notification on potential_project
DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1
                       FROM information_schema.columns
                       WHERE table_name = 'potential_project'
                         AND column_name = 'notification_date') THEN
            ALTER TABLE potential_project
                ADD COLUMN notification_date DATE;
        END IF;
    END
$$;
