package test.com.bulbview.recipeplanner.presenter

import org.springframework.beans.factory.annotation.Autowired

import test.com.bulbview.recipeplanner.dao.SpringContextTestFixture
import test.com.bulbview.recipeplanner.dao.TestUtilities

import com.bulbview.recipeplanner.datamodel.schedule.Schedule
import com.bulbview.recipeplanner.persistence.ScheduleObjectifyDao
import com.bulbview.recipeplanner.ui.manager.MainWindowView
import com.bulbview.recipeplanner.ui.manager.ScheduleHistoryListView
import com.bulbview.recipeplanner.ui.presenter.ScheduleHistoryPresenter


class ScheduleHistoryPresenterTest extends SpringContextTestFixture {

    @Autowired
    def ScheduleHistoryPresenter presenter
    @Autowired
    def ScheduleObjectifyDao scheduleDao
    def TestUtilities<Schedule> scheduleUtils
    def MainWindowView mockMainWindowView
    def ScheduleHistoryListView mockScheduleHistoryListView

    def setup() {
        mockMainWindowView = Mock()
        mockScheduleHistoryListView = Mock()
        presenter.setMainWindowView(mockMainWindowView)
        presenter.setView(mockScheduleHistoryListView)
    }

    def "should display schedule history dialog window on request" () {
        when:""
        presenter.showHistory()
        then:""
        1 * mockMainWindowView.showScheduleHistoryWindow()
    }

    def createAndSaveSchedule = {
        def schedule = new Schedule()
        schedule.setStartDate(it)
        return scheduleDao.save(schedule)
    }

    def "should display all schedules retrieved from persistence on startup" () {
        given:"a number of schedules are persisted"
        def schedule1 = createAndSaveSchedule(new Date(103,05,26))
        def schedule2 = createAndSaveSchedule(new Date(103,05,28))
        def schedule3 = createAndSaveSchedule(new Date(103,05,29))

        when:"the presenter is initialised"
        presenter.init()

        then:"schedules are added to the schedule history list view"
        1* mockScheduleHistoryListView.addListItem(schedule1)
        1* mockScheduleHistoryListView.addListItem(schedule2)
        1* mockScheduleHistoryListView.addListItem(schedule3)
    }
}
