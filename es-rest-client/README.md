使用说明：

	1.安装ES;
	2.src/test/java下的zhaohe.study.es.operation.test包下单元测试class查看效果
	
PS:

	1.Elastcisearch 是分布式的 文档 存储。它能存储和检索复杂的数据结构--序列化成为JSON文档--以 实时 的方式。 换句话说，一旦一个文档
	被存储在Elasticsearch 中，它就是可以被集群中的任意节点检索到
	2.文档元数据：_index/_type_id
	3.在 Elasticsearch 中的所有类型最终都共享相同的映射，例如以下2个类型people和transactions。实际上的映射是共享的，多个类型可
	以在相同的索引中存在，只要它们的字段不冲突（要么因为字段是互为独占模式，要么因为它们共享相同的字段）。
	类型不适合 完全不同类型的数据 。如果两个类型的字段集是互不相同的，这就意味着索引中将有一半的数据是空的（字段将是 稀疏的 ），最终将导
	致性能问题。
	在这种情况下，最好是使用两个单独的索引。
	例如：索引person_index下的2个类型为student_type和teacher_type是可以的；但是如果类型是fish_type和bird_type是2个类型，那
	么应该换成2个索引：bird_index/bird_type和fish_index/fish_type，因为：
	----------------------一个索引-----------------------------------------------
	index               	mapping        	type(类型最终都共享相同的映射)
	only_one_index		bird_fly		bird(fish_*是空的)
				    	bird_air
				
					fish_swim		fish(bird_*是空的)
					fish_water
	----------------------应该改为2个索引------------------------------------------
	index               	mapping        	type(类型最终都共享相同的映射)
	bird_index			bird_fly		bird
				    	bird_air
				
	fish_index			fish_swim		fish
					fish_water
	(类似于继承的概念，例如对于person的索引，student和teacher是2种类型)
		"people": {
            "properties": {
               "name": {
                  "type": "string",
               },
               "address": {
                  "type": "string"
               }
            }
         },
         "transactions": {
            "properties": {
               "timestamp": {
                  "type": "date",
                  "format": "strict_date_optional_time"
               },
               "message": {
                  "type": "string"
               }
            }
         }
    	4.在内部，Elasticsearch 已将旧文档标记为已删除，并增加一个全新的文档。 尽管你不能再对旧版本的文档进行访问，但它并不会立即消
    	失。当继续索引更多的数据，Elasticsearch 会在后台清理这些已删除文档。
    	5.悲观并发控制
	这种方法被关系型数据库广泛使用，它假定有变更冲突可能发生，因此阻塞访问资源以防止冲突。 一个典型的例子是读取一行数据之前先将其锁
	住，确保只有放置锁的线程能够对这行数据进行修改。
	6.乐观并发控制
	Elasticsearch 中使用的这种方法假定冲突是不可能发生的，并且不会阻塞正在尝试的操作。 然而，如果源数据在读写当中被修改，更新将会失
	败。应用程序接下来将决定该如何解决冲突。 例如，可以重试更新、使用新的数据、或者将相关情况报告给用户。
	7.Elasticsearch 是分布式的。当文档创建、更新或删除时， 新版本的文档必须复制到集群中的其他节点。Elasticsearch 也是异步和并发
	的，这意味着这些复制请求被并行发送，并且到达目的地时也许 顺序是乱的 。 Elasticsearch 需要一种方法确保文档的旧版本不会覆盖新的版
	本。当我们之前讨论 index ， GET 和 delete 请求时，我们指出每个文档都有一个 _version （版本）号，当文档被修改时版本号递增。
	Elasticsearch 使用这个 _version 号来确保变更以正确顺序得到执行。如果旧版本的文档在新版本之后到达，它可以被简单的忽略。
	我们可以利用 _version 号来确保 应用中相互冲突的变更不会导致数据丢失。我们通过指定想要修改文档的 version 号来达到这个目的。 如果
	该版本不是当前版本号，我们的请求将会失败。
	外部版本号的处理
	外部版本号的处理方式和我们之前讨论的内部版本号的处理方式有些不同， Elasticsearch 不是检查当前 _version 和请求中指定的版本号是
	否相同， 而是检查当前 _version 是否 小于 指定的版本号。 如果请求成功，外部的版本号作为文档的新 _version 进行存储。
	8. bulk 请求不是原子的： 不能用它来实现事务控制。每个请求是单独处理的，因此一个请求的成功或失败不会影响其他的请求。
	一个好的办法是开始时将 1,000 到 5,000 个文档作为一个批次, 如果你的文档非常大，那么就减少批量的文档个数。
	密切关注你的批量请求的物理大小往往非常有用，一千个 1KB 的文档是完全不同于一千个 1MB 文档所占的物理大小。 一个好的批量大小在开始处
	理后所占用的物理大小约为 5-15 MB。
	