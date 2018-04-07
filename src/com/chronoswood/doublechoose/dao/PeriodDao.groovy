package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Period
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.ResponseStatus

@Mapper
@Repository
interface PeriodDao {
    @Select('''
SELECT * FROM period WHERE NOW() >= begin AND NOW() <= end
ORDER BY begin DESC
LIMIT 1
''')
    Period queryLatestPeriod();
}