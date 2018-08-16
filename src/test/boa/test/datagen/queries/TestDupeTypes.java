package boa.test.datagen.queries;

import org.junit.Test;

public class TestDupeTypes extends QueryTest {

	@Test
	public void testDupeTypes() {
		String expected = "types[140492550][Account] = src/JLS8/LambdaDemo/v10/LambdaDemo.java\n"
				+ "types[140492550][Account] = src/JLS8/LambdaDemo/v9/LambdaDemo.java\n"
				+ "types[140492550][Account] = src/JLS8/RADemo/Account.java\n"
				+ "types[140492550][Accountant] = src/JLS2/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][Accountant] = src/JLS3/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][Accountant] = src/JLS3/GenDemo/v2/GenDemo2.java\n"
				+ "types[140492550][AnnDemo] = src/JLS3/AnnDemo/v4/AnnDemo.java\n"
				+ "types[140492550][AnnDemo] = src/JLS3/AnnDemo/v5/AnnDemo.java\n"
				+ "types[140492550][AnnDemo] = src/JLS3/AnnProcDemo/AnnDemo.java\n"
				+ "types[140492550][AnnDemo] = src/JLS3/AnnDemo/v3/AnnDemo.java\n"
				+ "types[140492550][AnnDemo] = src/JLS3/AnnDemo/v1/AnnDemo.java\n"
				+ "types[140492550][AnnDemo] = src/JLS3/AnnDemo/v2/AnnDemo.java\n"
				+ "types[140492550][AnnProcDemo] = src/JLS3/AnnProcDemo/AnnProcDemo.java\n"
				+ "types[140492550][AssertDemo] = src/JLS2/AssertDemo/v1/AssertDemo.java\n"
				+ "types[140492550][AssertDemo] = src/JLS2/AssertDemo/v4/AssertDemo.java\n"
				+ "types[140492550][AssertDemo] = src/JLS2/AssertDemo/v2/AssertDemo.java\n"
				+ "types[140492550][AssertDemo] = src/JLS2/AssertDemo/v3/AssertDemo.java\n"
				+ "types[140492550][BinLitDemo] = src/JLS4/BinLitDemo/BinLitDemo.java\n"
				+ "types[140492550][BoxDemo] = src/JLS3/BoxDemo/v2/BoxDemo.java\n"
				+ "types[140492550][BoxDemo] = src/JLS4/BoxDemo/BoxDemo.java\n"
				+ "types[140492550][BoxDemo] = src/JLS3/BoxDemo/v1/BoxDemo.java\n"
				+ "types[140492550][Box] = src/JLS4/BoxDemo/Box.java\n"
				+ "types[140492550][BreakageDemo] = src/JLS4/BreakageDemo/BreakageDemo.java\n"
				+ "types[140492550][Car] = src/JLS8/DMDemo/v3/DMDemo.java\n"
				+ "types[140492550][Car] = src/JLS8/DMDemo/v2/DMDemo.java\n"
				+ "types[140492550][Car] = src/JLS8/DMDemo/v1/DMDemo.java\n"
				+ "types[140492550][CardboardFactory] = src/JLS3/CovarDemo/v2/CovarDemo.java\n"
				+ "types[140492550][CardboardFactory] = src/JLS3/CovarDemo/v1/CovarDemo.java\n"
				+ "types[140492550][Cardboard] = src/JLS3/CovarDemo/v2/CovarDemo.java\n"
				+ "types[140492550][Cardboard] = src/JLS3/CovarDemo/v1/CovarDemo.java\n"
				+ "types[140492550][Container] = src/JLS2/GenDemo/v1/GenDemo.java\n"
				+ "types[140492550][Container] = src/JLS3/GenDemo/v1/GenDemo.java\n"
				+ "types[140492550][Container] = src/JLS3/GenDemo/v1/GenDemo1.java\n"
				+ "types[140492550][CopyToDatabaseOrFile] = src/JLS4/CopyToDatabaseOrFile/v1/CopyToDatabaseOrFile.java\n"
				+ "types[140492550][CopyToDatabaseOrFile] = src/JLS4/CopyToDatabaseOrFile/v2/CopyToDatabaseOrFile.java\n"
				+ "types[140492550][Copy] = src/JLS4/Copy/v1/Copy.java\n"
				+ "types[140492550][Copy] = src/JLS4/Copy/v2/Copy.java\n"
				+ "types[140492550][CovarDemo] = src/JLS3/CovarDemo/v2/CovarDemo.java\n"
				+ "types[140492550][CovarDemo] = src/JLS3/CovarDemo/v1/CovarDemo.java\n"
				+ "types[140492550][DMDemo] = src/JLS8/DMDemo/v3/DMDemo.java\n"
				+ "types[140492550][DMDemo] = src/JLS8/DMDemo/v4/DMDemo.java\n"
				+ "types[140492550][DMDemo] = src/JLS8/DMDemo/v2/DMDemo.java\n"
				+ "types[140492550][DMDemo] = src/JLS8/DMDemo/v1/DMDemo.java\n"
				+ "types[140492550][Drawable] = src/JLS8/SMDemo/Drawable.java\n"
				+ "types[140492550][Drivable] = src/JLS8/DMDemo/v1/Drivable.java\n"
				+ "types[140492550][Drivable] = src/JLS8/DMDemo/v2/Drivable.java\n"
				+ "types[140492550][Drivable] = src/JLS8/DMDemo/v3/Drivable.java\n"
				+ "types[140492550][Employee] = src/JLS2/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][Employee] = src/JLS3/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][Employee] = src/JLS3/GenDemo/v2/GenDemo2.java\n"
				+ "types[140492550][Employee] = src/JLS3/EnForLoopDemo/v1/EnForLoopDemo.java\n"
				+ "types[140492550][EnForLoopDemo] = src/JLS3/EnForLoopDemo/v2/EnForLoopDemo.java\n"
				+ "types[140492550][EnForLoopDemo] = src/JLS3/EnForLoopDemo/v1/EnForLoopDemo.java\n"
				+ "types[140492550][Filter] = src/JLS3/GenDemo/v5/GenDemo5.java\n"
				+ "types[140492550][Filter] = src/JLS3/GenDemo/v5/GenDemo.java\n"
				+ "types[140492550][Filter] = src/JLS2/GenDemo/v5/GenDemo.java\n"
				+ "types[140492550][GenDemo.] = src/JLS3/GenDemo/v5/GenDemo5.java\n"
				+ "types[140492550][GenDemo.] = src/JLS3/GenDemo/v5/GenDemo.java\n"
				+ "types[140492550][GenDemo.] = src/JLS2/GenDemo/v5/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS2/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS2/GenDemo/v1/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS2/GenDemo/v4/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v5/GenDemo5.java\n"
				+ "types[140492550][GenDemo] = src/JLS2/GenDemo/v5/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v3/GenDemo3.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v1/GenDemo1.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v4/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v3/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v5/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v1/GenDemo.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v4/GenDemo4.java\n"
				+ "types[140492550][GenDemo] = src/JLS3/GenDemo/v2/GenDemo2.java\n"
				+ "types[140492550][GenDemo] = src/JLS2/GenDemo/v3/GenDemo.java\n"
				+ "types[140492550][HeapPollutionDemo] = src/JLS4/HeapPollutionDemo/HeapPollutionDemo.java\n"
				+ "types[140492550][LambdaDemo.] = src/JLS8/LambdaDemo/v7/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo.] = src/JLS8/LambdaDemo/v2/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo.] = src/JLS8/LambdaDemo/v4/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v1/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v3/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v6/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v7/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v2/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v10/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v4/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v9/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v8/LambdaDemo.java\n"
				+ "types[140492550][LambdaDemo] = src/JLS8/LambdaDemo/v5/LambdaDemo.java\n"
				+ "types[140492550][Light] = src/JLS3/StaticImportsDemo/v1/Light.java\n"
				+ "types[140492550][MRDemo.] = src/JLS8/MRDemo/v3/MRDemo.java\n"
				+ "types[140492550][MRDemo.] = src/JLS8/MRDemo/v2/MRDemo.java\n"
				+ "types[140492550][MRDemo] = src/JLS8/MRDemo/v1/MRDemo.java\n"
				+ "types[140492550][MRDemo] = src/JLS8/MRDemo/v3/MRDemo.java\n"
				+ "types[140492550][MRDemo] = src/JLS8/MRDemo/v4/MRDemo.java\n"
				+ "types[140492550][MRDemo] = src/JLS8/MRDemo/v5/MRDemo.java\n"
				+ "types[140492550][MRDemo] = src/JLS8/MRDemo/v6/MRDemo.java\n"
				+ "types[140492550][MRDemo] = src/JLS8/MRDemo/v2/MRDemo.java\n"
				+ "types[140492550][Marble] = src/JLS4/BoxDemo/Marble.java\n"
				+ "types[140492550][MonitorEngine] = src/JLS4/MonitorEngine/v2/MonitorEngine.java\n"
				+ "types[140492550][MonitorEngine] = src/JLS4/MonitorEngine/v1/MonitorEngine.java\n"
				+ "types[140492550][PNG] = src/JLS2/AssertDemo/v3/AssertDemo.java\n"
				+ "types[140492550][PaperFactory] = src/JLS3/CovarDemo/v2/CovarDemo.java\n"
				+ "types[140492550][PaperFactory] = src/JLS3/CovarDemo/v1/CovarDemo.java\n"
				+ "types[140492550][Paper] = src/JLS3/CovarDemo/v2/CovarDemo.java\n"
				+ "types[140492550][Paper] = src/JLS3/CovarDemo/v1/CovarDemo.java\n"
				+ "types[140492550][Planets] = src/JLS4/Planets/Planets.java\n"
				+ "types[140492550][PressureException] = src/JLS4/MonitorEngine/v2/MonitorEngine.java\n"
				+ "types[140492550][PressureException] = src/JLS4/MonitorEngine/v1/MonitorEngine.java\n"
				+ "types[140492550][SortInts.] = src/JLS4/SortInts/v1/SortInts.java\n"
				+ "types[140492550][SortInts.] = src/JLS4/SortInts/v2/SortInts.java\n"
				+ "types[140492550][SortInts] = src/JLS4/SortInts/v1/SortInts.java\n"
				+ "types[140492550][SortInts] = src/JLS4/SortInts/v2/SortInts.java\n"
				+ "types[140492550][SortedEmployees] = src/JLS2/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][SortedEmployees] = src/JLS3/GenDemo/v2/GenDemo.java\n"
				+ "types[140492550][SortedEmployees] = src/JLS3/GenDemo/v2/GenDemo2.java\n"
				+ "types[140492550][StaticImportsDemo] = src/JLS3/StaticImportsDemo/v1/StaticImportsDemo.java\n"
				+ "types[140492550][StaticImportsDemo] = src/JLS3/StaticImportsDemo/v2/StaticImportsDemo.java\n"
				+ "types[140492550][SubException1] = src/JLS4/BreakageDemo/BreakageDemo.java\n"
				+ "types[140492550][SubException2] = src/JLS4/BreakageDemo/BreakageDemo.java\n"
				+ "types[140492550][SuperException] = src/JLS4/BreakageDemo/BreakageDemo.java\n"
				+ "types[140492550][Switchable] = src/JLS3/StaticImportsDemo/v1/Switchable.java\n"
				+ "types[140492550][TEDemo] = src/JLS3/TEDemo/v1/TEDemo.java\n"
				+ "types[140492550][TEDemo] = src/JLS3/TEDemo/v2/TEDemo.java\n"
				+ "types[140492550][TemperatureException] = src/JLS4/MonitorEngine/v2/MonitorEngine.java\n"
				+ "types[140492550][TemperatureException] = src/JLS4/MonitorEngine/v1/MonitorEngine.java\n"
				+ "types[140492550][ToDo] = src/JLS3/AnnDemo/v4/ToDo.java\n"
				+ "types[140492550][ToDo] = src/JLS8/RADemo/ToDo.java\n"
				+ "types[140492550][ToDo] = src/JLS3/AnnProcDemo/ToDo.java\n"
				+ "types[140492550][ToDo] = src/JLS3/AnnDemo/v3/ToDo.java\n"
				+ "types[140492550][ToDo] = src/JLS3/AnnDemo/v5/ToDo.java\n"
				+ "types[140492550][ToDo] = src/JLS3/AnnDemo/v2/ToDo.java\n"
				+ "types[140492550][TransientDemo.Transient] = src/JLS2/TransientDemo/Transient.java\n"
				+ "types[140492550][UndLitDemo] = src/JLS4/UndLitDemo/UndLitDemo.java\n"
				+ "types[140492550][UnsafeVarargsDemo2] = src/JLS4/UnsafeVarargsDemo/UnsafeVarargsDemo2.java\n"
				+ "types[140492550][UnsafeVarargsDemo] = src/JLS4/UnsafeVarargsDemo/UnsafeVarargsDemo.java\n"
				+ "types[140492550][Volatile.VolatileDemo] = src/JLS2/Volatile/VolatileDemo.java\n"
				+ "types[140492550][foo.Light] = src/JLS3/StaticImportsDemo/v2/foo/Light.java\n"
				+ "types[140492550][foo.Switchable] = src/JLS3/StaticImportsDemo/v2/foo/Switchable.java\n";
		queryTest("test/known-good/dupe-types.boa", expected);
	}
}