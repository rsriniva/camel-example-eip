package com.buildria.camel.example.basis.camelcontext;

import com.buildria.camel.example.JunitBase;
import java.io.File;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.util.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CamelContextShutdownRunningTaskTest extends JunitBase {

    private static final String WORKDIR = "target/shutdownRunningTask/CompleteAllTasks";

    private static final String ROUTE_NAME = "CompleteAllTasks";

    private static final int TESTFILE_NUM = 50;

    private static final int EXTRAFILE_NUM = 10;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected CamelContext camelContext;

    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;

    private File workDir;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        template = camelContext.createProducerTemplate();
        workDir = new File(WORKDIR);
        if (workDir.getParentFile().exists()) {
            FileUtil.removeDir(workDir.getParentFile());
        }
        workDir.mkdirs();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        if (workDir.getParentFile().exists()) {
            FileUtil.removeDir(workDir.getParentFile());
        }
    }

    /**
     * ディレクトにあるファイルをポーリングで取得します。
     * ポーリング開始後、新たにディレクトリにファイルを追加後、
     * ルートを停止します。
     * 
     * ShutdownRunningTaskがCompleteAllTasksの場合、終了処理が始まると、
     * ポーリング実施時に対象としたァイルがすべて完了するまで終了を待ちますが、
     * ポーリング中に追加されたファイルは、処理されません。
     * 
     * @throws Exception 
     */
    @Test
    @DirtiesContext
    public void testCompleteAllTasks() throws Exception {
        // テストファイル生成
        log.debug("★ テスト用ファイルを{}個作成します。", TESTFILE_NUM);
        for (int i = 0; i < TESTFILE_NUM; i++) {
            File f = new File(workDir, "test" + String.format("%04d", i) + ".txt");
            f.createNewFile();
        }

        // 1回目のポーリングで処理対象になったファイルはすべて処理される
        resultEndpoint.expectedMessageCount(TESTFILE_NUM);

        // ルートを開始
        camelContext.startRoute(ROUTE_NAME);

        Thread.sleep(3000);

        // ポーリングが開始した後に、追加でファイルを作成します。
        // このファイルが処理されるかどうか確認します。
        log.debug("★ 追加のファイルを{}個作成します。", EXTRAFILE_NUM);
        for (int i = 0; i < EXTRAFILE_NUM; i++) {
            File f = new File(workDir, "extra" + String.format("%04d", i) + ".txt");
            f.createNewFile();
        }

        // ルートを停止
        camelContext.stopRoute(ROUTE_NAME);

        // 期待値の確認
        resultEndpoint.assertIsSatisfied();
    }

}
